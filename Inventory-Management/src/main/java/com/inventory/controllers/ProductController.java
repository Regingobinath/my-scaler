package com.inventory.controllers;

import com.inventory.dtos.DeliveryEstimateRequestDto;
import com.inventory.dtos.DeliveryEstimateResponseDto;
import com.inventory.dtos.ResponseStatus;
import com.inventory.services.ProductService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public DeliveryEstimateResponseDto estimateDeliveryTime(DeliveryEstimateRequestDto requestDto){
        DeliveryEstimateResponseDto responseDto=new DeliveryEstimateResponseDto();
        try{
            Date estimateDeliveryDate = productService.estimateDeliveryDate(requestDto.getProductId(), requestDto.getAddressId());
            responseDto.setExpectedDeliveryDate(estimateDeliveryDate);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
