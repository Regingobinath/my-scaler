package com.inventory.services;


import com.inventory.exceptions.OrderCannotBeCancelledException;
import com.inventory.exceptions.OrderDoesNotBelongToUserException;
import com.inventory.exceptions.OrderNotFoundException;
import com.inventory.exceptions.UserNotFoundException;
import com.inventory.models.Order;

public interface OrderService {
    public Order cancelOrder(int orderId, int userId)  throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException;
}
