package com.inventory.services;

import com.inventory.exceptions.AddressNotFoundException;
import com.inventory.exceptions.ProductNotFoundException;

import java.util.Date;

public interface ProductService {
    public Date estimateDeliveryDate(
            int productId, int addressId)
            throws ProductNotFoundException, AddressNotFoundException;
}
