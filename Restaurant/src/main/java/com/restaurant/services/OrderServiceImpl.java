package com.restaurant.services;

import com.restaurant.exceptions.CustomerSessionNotFound;
import com.restaurant.exceptions.InvalidMenuItem;
import com.restaurant.exceptions.UserNotFoundException;
import com.restaurant.models.*;
import com.restaurant.repositories.CustomerSessionRepository;
import com.restaurant.repositories.MenuItemRepository;
import com.restaurant.repositories.OrderRepository;
import com.restaurant.repositories.UserRepository;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{
    
    private final OrderRepository orderRepo;
    private final CustomerSessionRepository custSessionRepo;
    private final UserRepository userRepo;
    private final MenuItemRepository menuItemRepo;
    private static final double GST = 5;
    private static final double SERVICE_CHARGE = 10;

    public OrderServiceImpl(
            OrderRepository orderRepo, CustomerSessionRepository custSessionRepo,
            UserRepository userRepo, MenuItemRepository menuItemRepo) {
        this.orderRepo = orderRepo;
        this.custSessionRepo = custSessionRepo;
        this.userRepo = userRepo;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public Bill generateBill(long userId) throws CustomerSessionNotFound {
        Optional<CustomerSession> custSession = this.custSessionRepo.findActiveCustomerSessionByUserId(userId);
        if(custSession.isEmpty()) {
            throw new CustomerSessionNotFound("Customer Session not found");
        }

        List<Order> orders = this.orderRepo.findOrdersByCustomerSession(custSession.get().getId());

        Bill bill = new Bill();
        bill.setOrderedItems(this.getAllMenuItems(orders));
        bill.setTotalAmount(this.calculateTotalPrice(orders));
        bill.setGst(this.calculateTotalGST(orders));
        bill.setServiceCharge(this.calculateTotalServiceCharge(orders));

        custSession.get().setCustomerSessionStatus(CustomerSessionStatus.ENDED);
        this.custSessionRepo.save(custSession.get());

        return bill;
    }

    @Override
    public Order placeOrder(long userId, Map<Long, Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem {
        Optional<User> user = this.userRepo.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        Map<MenuItem, Integer> items = new HashMap<>();
        for(Map.Entry<Long, Integer> entry : orderedItems.entrySet()) {
            Optional<MenuItem> item = this.menuItemRepo.findById(entry.getKey());
            if (item.isEmpty()) {
                throw new InvalidMenuItem("MenuItem not found");
            }
            items.put(item.get(), entry.getValue());
        }

        Optional<CustomerSession> session = this.custSessionRepo.findActiveCustomerSessionByUserId(userId);
        CustomerSession customerSession;
        if(session.isEmpty()) {
            customerSession = new CustomerSession();
            customerSession.setUser(user.get());
            customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            this.custSessionRepo.save(customerSession);
        } else {
            customerSession = session.get();
        }

        Order order = new Order();
        order.setOrderedItems(items);
        order.setCustomerSession(customerSession);

        this.orderRepo.save(order);

        return order;
    }

    private Map<MenuItem, Integer> getAllMenuItems(List<Order> orders) {
        return orders.stream().map(order -> order.getOrderedItems().entrySet())
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)));
    }

    private double calculateTotalPrice(List<Order> orders) {
        BiFunction<MenuItem, Integer, Double> menuPrice = (entry, count) -> {
            return (entry.getPrice() +(entry.getPrice() * GST / 100) + (entry.getPrice() * SERVICE_CHARGE / 100)) * count;
        };

        Function<Map<MenuItem, Integer>, Double> orderPrice = (items) -> {
            return items.entrySet().stream().mapToDouble(
                    entry -> menuPrice.apply(entry.getKey(), entry.getValue())).sum();//reduce(0d, (a, b) -> a + b);
        };

        return orders.stream().mapToDouble(item -> orderPrice.apply(item.getOrderedItems())).sum();
    }

    private double calculateTotalGST(List<Order> orders) {
        BiFunction<MenuItem, Integer, Double> menuGst = (entry, count) -> {
            return (entry.getPrice() * GST / 100) * count;
        };

        Function<Map<MenuItem, Integer>, Double> orderPrice = (items) -> {
            return items.entrySet().stream().mapToDouble(
                    entry -> menuGst.apply(entry.getKey(), entry.getValue())).sum();//reduce(0d, (a, b) -> a + b);
        };

        return orders.stream().mapToDouble(item -> orderPrice.apply(item.getOrderedItems())).sum();
    }

    private double calculateTotalServiceCharge(List<Order> orders) {
        BiFunction<MenuItem, Integer, Double> menuServiceCharge = (entry, count) -> {
            return (entry.getPrice() * SERVICE_CHARGE / 100) * count;
        };

        Function<Map<MenuItem, Integer>, Double> orderPrice = (items) -> {
            return items.entrySet().stream().mapToDouble(
                    entry -> menuServiceCharge.apply(entry.getKey(), entry.getValue())).sum();//reduce(0d, (a, b) -> a + b);
        };

        return orders.stream().mapToDouble(item -> orderPrice.apply(item.getOrderedItems())).sum();
    }

}
