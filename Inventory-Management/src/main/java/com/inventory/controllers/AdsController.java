package com.inventory.controllers;

import com.inventory.dtos.GetAdvertisementForUserRequestDto;
import com.inventory.dtos.GetAdvertisementForUserResponseDto;
import com.inventory.dtos.ResponseStatus;
import com.inventory.exceptions.UserNotFoundException;
import com.inventory.models.Advertisement;
import com.inventory.services.AdsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Controller
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }
    public GetAdvertisementForUserResponseDto getAdvertisementForUser(GetAdvertisementForUserRequestDto requestDto){
        GetAdvertisementForUserResponseDto responseDto = new GetAdvertisementForUserResponseDto();
        try {
            Advertisement advertisement = this.adsService.getAdvertisementForUser(requestDto.getUserId());
            responseDto.setAdvertisement(advertisement);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;

    }
}
