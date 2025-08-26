package com.restaurant.controllers;

import com.restaurant.dtos.*;
import com.restaurant.exceptions.CustomerSessionNotFound;
import com.restaurant.models.Bill;
import com.restaurant.models.Order;
import com.restaurant.services.OrderService;

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public GenerateBillResponseDto generateBill(GenerateBillRequestDto requestDto){
        GenerateBillResponseDto response = new GenerateBillResponseDto();
        try {
            Bill bill = this.orderService.generateBill(requestDto.getUserId());
            response.setBill(bill);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (CustomerSessionNotFound e) {
            System.out.println(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }

    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto requestDto){
        PlaceOrderResponseDto responseDto = new PlaceOrderResponseDto();
        try {
            Order order = this.orderService.placeOrder(requestDto.getUserId(), requestDto.getOrderedItems());
            responseDto.setOrder(order);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }

    public static void main(String[] args) {
        String name_1 = "JACKUB ROZARIO";
        String name_2 = "JACQUELINE";
        String flames = "FLAMES";

        name_1 = name_1.replace(" ","");
        name_2 = name_2.replace(" ","");

        //remove same occurrences
        for(int i= 0; i < name_1.length(); i++) {
            int oldLen = name_2.length();
            name_2 = name_2.replace(name_1.charAt(i)+"", "");
            if(oldLen != name_2.length()) {
                oldLen = name_1.length();
                name_1 = name_1.replace(name_1.charAt(i)+"", "");
                if (oldLen != name_1.length()) {
                    i--;
                }
            }
        }

        StringBuilder result = new StringBuilder(flames);
        int nameLength = name_1.length() + name_2.length();

        while (result.length() > 1) {
            int remainder = nameLength % result.length();
            if(remainder == 0) {
                remainder = result.length();
            }
            System.out.println("Remove " + result.charAt(remainder -1));
            result.deleteCharAt(remainder - 1);
        }

        System.out.println("Your are : " + result.toString() + " to him/her");
    }
}