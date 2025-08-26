package com.inventory.dtos;

import com.inventory.models.Inventory;
import lombok.Data;

@Data
public class CreateOrUpdateResponseDto {
    private Inventory inventory;
    private ResponseStatus responseStatus;
}
