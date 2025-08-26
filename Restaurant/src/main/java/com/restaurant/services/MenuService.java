package com.restaurant.services;

import com.restaurant.exceptions.InvalidDietaryPreference;
import com.restaurant.exceptions.UnAuthorizedAccess;
import com.restaurant.exceptions.UserNotFoundException;
import com.restaurant.models.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> getMenuItems(String itemType) throws InvalidDietaryPreference;
    MenuItem addMenuItem(
            long userId, String name, double price,
            String dietaryRequirement, String itemType,
            String description) throws UserNotFoundException, UnAuthorizedAccess;
}
