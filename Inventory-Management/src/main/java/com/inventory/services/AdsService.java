package com.inventory.services;


import com.inventory.exceptions.UserNotFoundException;
import com.inventory.models.Advertisement;

public interface AdsService {
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException;
}
