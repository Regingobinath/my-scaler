package com.inventory.services;

import com.inventory.exceptions.*;
import com.inventory.models.*;
import com.inventory.repositories.*;
import org.hibernate.Hibernate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;


@Service
public class OrderServiceImpl implements OrderService{
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final HighDemandProductRepository highDemandProductRepository;
    private final InventoryRepository inventoryRepository;
    private final ReentrantLock lock = new ReentrantLock();
    public OrderServiceImpl(
            OrderRepository orderRepository, AddressRepository addressRepository,
            OrderDetailRepository orderDetailRepository, ProductRepository productRepository,
            HighDemandProductRepository highDemandProductRepository,
            UserRepository userRepository, InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.highDemandProductRepository = highDemandProductRepository;
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails)
            throws UserNotFoundException, InvalidAddressException,
            OutOfStockException, InvalidProductException, HighDemandProductException {
        Optional<User> user = this.userRepository.findById(userId);
        user.orElseThrow(() -> new UserNotFoundException("User Not found Exception"));

        Optional<Address> address = this.addressRepository.findById(addressId);
        address.orElseThrow(() -> new InvalidAddressException("Address not found"));

        Hibernate.initialize(user.get().getAddresses());
        if(user.get().getAddresses().stream().noneMatch(add -> add.getId() == address.get().getId())) {
            throw new InvalidAddressException("Invalid address");
        }
        System.out.println("userId = " + userId + ", addressId = " + addressId + ", orderDetails = " + orderDetails);

        return this.placeOrder(user.get(), address.get(), orderDetails);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    private Order placeOrder(User user, Address deliveryAddress, List<Pair<Integer, Integer>> orderDetails) throws OutOfStockException, InvalidProductException, HighDemandProductException{
        List<Integer> productIds = orderDetails.stream().map(pair -> pair.getFirst()).toList();

        List<Inventory> inventories = this.inventoryRepository.findAllByProductIdIn(productIds);
        if(inventories.size() != productIds.size()) {
            throw new InvalidProductException("Product not found");
        }

        BiPredicate<Inventory, List<Pair<Integer, Integer>>> isQtyNotAvail =
                (inv, orderDel) -> inv.getQuantity() < orderDetails.stream().filter(pair -> pair.getFirst() == (int)inv.getProduct().getId()).findFirst().get().getSecond();

        boolean noStock = inventories.stream().anyMatch(inventory -> isQtyNotAvail.test(inventory, orderDetails));
        if (noStock) {
            throw new OutOfStockException("Product out of stock");
        }

        //check for high demand product
        List<HighDemandProduct> hdProducts = this.highDemandProductRepository.findAllByProductIdIn(productIds);
        BiPredicate<HighDemandProduct, List<Pair<Integer, Integer>>> isProdHighDemand =
                (inv, orderDel) -> inv.getMaxQuantity() < orderDetails.stream().filter(pair -> pair.getFirst() == (int)inv.getProduct().getId()).findFirst().get().getSecond();
        boolean isHighDemand = hdProducts.stream().anyMatch(hdprod -> isProdHighDemand.test(hdprod, orderDetails));
        if (isHighDemand) {
            throw new HighDemandProductException("Product high demand");
        }
        Order order = new Order();
        order.setUser(user);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderStatus(OrderStatus.PLACED);
        List<OrderDetail> orderDetailList = orderDetails.stream().map(pair -> {
            OrderDetail detail = new OrderDetail();
            //detail.setOrder(order);
            detail.setQuantity(pair.getSecond());
            detail.setProduct(productRepository.findById(pair.getFirst()).get());
            return detail;
        }).toList();
        order.setOrderDetails(orderDetailList);

        inventories.forEach(inventory -> {
            Integer qty = orderDetails.stream().filter(orderDel -> orderDel.getFirst() == inventory.getProduct().getId()).findFirst().get().getSecond();
            inventory.setQuantity(inventory.getQuantity() - qty);
            }
        );
        this.inventoryRepository.saveAll(inventories);

        order = this.orderRepository.save(order);
        Hibernate.initialize(order.getOrderDetails());
        Order finalOrder = order;
        order.getOrderDetails().forEach(detail -> detail.setOrder(finalOrder));

        this.orderDetailRepository.saveAll(order.getOrderDetails());

        return order;
    }

    @Override
    @Transactional
    public Order cancelOrder(int orderId, int userId) throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException {
        Optional<User> user = this.userRepository.findById(userId);
        user.orElseThrow(() -> new UserNotFoundException("User not found"));

        Optional<Order> order = this.orderRepository.findById(orderId);
        order.orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if (user.get().getId() != order.get().getUser().getId()) {
            throw new OrderDoesNotBelongToUserException("The order does not belongs to the given user");
        }

        if(OrderStatus.getCompletedStatuses().contains(order.get().getOrderStatus())) {
            throw new OrderCannotBeCancelledException("This order already been Cancelled/Shipped/Delivered");
        }

        Order orderObj = null;
        try {
            lock.lock();
            List<OrderDetail> orderDetails = order.get().getOrderDetails();
            orderDetails.forEach(orderDetail -> {
                Optional<Inventory> inventory = this.inventoryRepository.findByProductId(
                        orderDetail.getProduct().getId());
                inventory.get().setQuantity(
                        inventory.get().getQuantity() + orderDetail.getQuantity()
                );

                this.inventoryRepository.save(inventory.get());
            });
            order.get().setOrderStatus(OrderStatus.CANCELLED);
            orderObj = this.orderRepository.save(order.get());
        } finally {
            lock.unlock();
        }

        return orderObj;
    }
}
