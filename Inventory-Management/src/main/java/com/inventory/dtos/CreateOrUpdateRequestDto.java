package com.inventory.dtos;

import lombok.*;

@Data
public class CreateOrUpdateRequestDto {
    private int userId;
    private int productId;
    private int quantity;
}
