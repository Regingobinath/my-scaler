package com.restaurant.repositories;


import com.restaurant.models.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    List<Order> findOrdersByCustomerSession(long customerSessionId);
}
