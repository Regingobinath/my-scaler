package com.inventory.services;

import com.inventory.exceptions.OrderCannotBeCancelledException;
import com.inventory.exceptions.OrderDoesNotBelongToUserException;
import com.inventory.exceptions.OrderNotFoundException;
import com.inventory.exceptions.UserNotFoundException;
import com.inventory.models.*;
import com.inventory.repositories.InventoryRepository;
import com.inventory.repositories.OrderDetailRepository;
import com.inventory.repositories.OrderRepository;
import com.inventory.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class OrderServiceImpl implements OrderService{
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final InventoryRepository inventoryRepository;
    private final ReentrantLock lock = new ReentrantLock();
    public OrderServiceImpl(
            OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
            UserRepository userRepository, InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
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
