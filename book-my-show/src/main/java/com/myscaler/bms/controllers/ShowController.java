package com.myscaler.bms.controllers;

import com.myscaler.bms.dtos.CreateShowRequestDTO;
import com.myscaler.bms.dtos.CreateShowResponseDTO;
import com.myscaler.bms.dtos.ResponseStatus;
import com.myscaler.bms.exeptions.*;
import com.myscaler.bms.models.Show;
import com.myscaler.bms.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {

    private ShowService showService;
    public ShowController(ShowService showService){
        this.showService = showService;
    }
    public CreateShowResponseDTO createShow(CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO responseDTO = new CreateShowResponseDTO();
        try{
            Show createdShow = showService.createShow(
                    requestDTO.getUserId(),
                    requestDTO.getMovieId(),
                    requestDTO.getScreenId(),
                    requestDTO.getStartTime(),
                    requestDTO.getEndTime(),
                    requestDTO.getPricingConfig(),
                    requestDTO.getFeatures()
            );
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setShow(createdShow);
        } catch (MovieNotFoundException | ScreenNotFoundException | FeatureNotSupportedByScreen
               | InvalidDateException | UserNotFoundException | UnAuthorizedAccessException ex){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
