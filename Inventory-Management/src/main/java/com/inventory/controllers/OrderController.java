package com.inventory.controllers;

import com.inventory.dtos.CancelOrderRequestDto;
import com.inventory.dtos.CancelOrderResponseDto;
import com.inventory.dtos.ResponseStatus;
import com.inventory.services.OrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    public CancelOrderResponseDto cancelOrder(CancelOrderRequestDto cancelOrderRequestDto) {
        CancelOrderResponseDto responseDto = new CancelOrderResponseDto();
        try {
            responseDto.setOrder(
                    this.orderService.cancelOrder(cancelOrderRequestDto.getOrderId(), cancelOrderRequestDto.getUserId()));
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDto.setStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }

}
