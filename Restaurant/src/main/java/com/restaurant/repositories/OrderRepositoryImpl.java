package com.restaurant.repositories;

import com.restaurant.models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository{
    
    private Map<Long, Order> orderStore;

    public OrderRepositoryImpl() {
        this.orderStore = new HashMap<>();
    }

    @Override
    public Order save(Order order) {
        if(order.getId() <= 0) {
            order.setId(RestaurantKeyGenerator.generateKey(this.orderStore.keySet()));
        }
        this.orderStore.put(order.getId(), order);
        return order;
    }

    @Override
    public List<Order> findOrdersByCustomerSession(long customerSessionId) {
        return this.orderStore.values().stream().filter(order -> order.getCustomerSession().getId() == customerSessionId).toList();
    }
    
}
