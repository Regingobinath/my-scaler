package com.inventory.services;


import com.inventory.exceptions.*;
import com.inventory.models.Notification;
import org.springframework.stereotype.Service;

public interface NotificationService {

    public Notification registerUser(int userId, int productId) throws UserNotFoundException, ProductNotFoundException, ProductInStockException;

    public void deregisterUser(int userId, int notificationId) throws UserNotFoundException, NotificationNotFoundException, UnAuthorizedException;
}
