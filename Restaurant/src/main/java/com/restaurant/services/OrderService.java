package com.restaurant.services;

import com.restaurant.exceptions.CustomerSessionNotFound;
import com.restaurant.exceptions.InvalidMenuItem;
import com.restaurant.exceptions.UserNotFoundException;
import com.restaurant.models.Bill;
import com.restaurant.models.Order;

import java.util.Map;

public interface OrderService {

    public Bill generateBill(long userId) throws CustomerSessionNotFound;

    public Order placeOrder(long userId, Map<Long,Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem;

}
