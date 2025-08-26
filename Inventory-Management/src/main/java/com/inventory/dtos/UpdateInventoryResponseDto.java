package com.inventory.dtos;

import com.inventory.models.Inventory;
import lombok.Data;

@Data
public class UpdateInventoryResponseDto {
    private Inventory inventory;
    private ResponseStatus responseStatus;
}
