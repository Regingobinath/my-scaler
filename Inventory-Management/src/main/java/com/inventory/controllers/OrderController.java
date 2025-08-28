package com.inventory.controllers;

import com.inventory.dtos.*;
import com.inventory.exceptions.*;
import com.inventory.models.Order;
import com.inventory.services.OrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) {
        PlaceOrderResponseDto responseDto = new PlaceOrderResponseDto();
        try {
            Order order = orderService.placeOrder(placeOrderRequestDto.getUserId(), placeOrderRequestDto.getAddressId(), placeOrderRequestDto.getOrderDetails());
            responseDto.setOrder(order);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | InvalidAddressException | OutOfStockException |
                 InvalidProductException |  HighDemandProductException e) {
            System.out.println(e.getMessage());
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
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
