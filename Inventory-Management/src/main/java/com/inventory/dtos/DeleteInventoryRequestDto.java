package com.inventory.dtos;

import lombok.Data;

@Data
public class DeleteInventoryRequestDto {
    private int userId;
    private int productId;
}
