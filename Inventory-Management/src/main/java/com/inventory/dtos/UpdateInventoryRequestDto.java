package com.inventory.dtos;

import lombok.Data;

@Data
public class UpdateInventoryRequestDto {
    private int productId;
    private int quantity;
}
