package com.inventory.services;

import com.inventory.exceptions.ProductNotFoundException;
import com.inventory.exceptions.UnAuthorizedAccessException;
import com.inventory.exceptions.UserNotFoundException;
import com.inventory.models.Inventory;

public interface InventoryService {
    public Inventory createOrUpdateInventory(int userId, int productId, int quantity) throws ProductNotFoundException, UserNotFoundException, UnAuthorizedAccessException;
    public void deleteInventory(int userId, int productId) throws  UserNotFoundException, UnAuthorizedAccessException;
    public Inventory updateInventory(int productId, int quantity) throws ProductNotFoundException;

}
