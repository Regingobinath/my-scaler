package com.inventory.controllers;

import com.inventory.dtos.*;
import com.inventory.models.Inventory;
import com.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {


    @Autowired
    private InventoryService inventoryService;

    public CreateOrUpdateResponseDto createOrUpdateInventory(CreateOrUpdateRequestDto requestDto){
        CreateOrUpdateResponseDto createOrUpdateResponseDto = new CreateOrUpdateResponseDto();
        try {
            Inventory inv = inventoryService.createOrUpdateInventory(requestDto.getUserId(), requestDto.getProductId(), requestDto.getQuantity());
            createOrUpdateResponseDto.setInventory(inv);
            createOrUpdateResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return createOrUpdateResponseDto;
        }
        catch (Exception e)
        {
            createOrUpdateResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            return createOrUpdateResponseDto;
        }
    }

    public DeleteInventoryResponseDto deleteInventory(DeleteInventoryRequestDto requestDto){
        DeleteInventoryResponseDto deleteInventoryResponseDto = new DeleteInventoryResponseDto();
        try {
            inventoryService.deleteInventory(requestDto.getUserId(),requestDto.getProductId());
            deleteInventoryResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return deleteInventoryResponseDto;
        }
        catch (Exception e)
        {
            deleteInventoryResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            return deleteInventoryResponseDto;
        }
    }

    public UpdateInventoryResponseDto updateInventory(UpdateInventoryRequestDto requestDto) {
        UpdateInventoryResponseDto responseDto = new UpdateInventoryResponseDto();
        try{
            Inventory inventory = inventoryService.updateInventory(requestDto.getProductId(), requestDto.getQuantity());
            responseDto.setInventory(inventory);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
