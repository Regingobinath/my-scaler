package com.inventory.services;


import com.inventory.exceptions.*;
import com.inventory.models.Order;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails)
            throws UserNotFoundException, InvalidAddressException, OutOfStockException,
            InvalidProductException, HighDemandProductException;
    public Order cancelOrder(int orderId, int userId)  throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException;
}
