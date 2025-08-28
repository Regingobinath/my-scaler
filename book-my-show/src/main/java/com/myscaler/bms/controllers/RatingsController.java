package com.myscaler.bms.controllers;

import com.myscaler.bms.dtos.*;
import com.myscaler.bms.exeptions.MovieNotFoundException;
import com.myscaler.bms.exeptions.UserNotFoundException;
import com.myscaler.bms.models.Rating;
import com.myscaler.bms.services.RatingsService;
import com.myscaler.bms.services.RatingsServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingsController {
    private final RatingsService ratingsService;
    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }
    public RateMovieResponseDto rateMovie(RateMovieRequestDto requestDto){
        RateMovieResponseDto responseDto = new RateMovieResponseDto();
        try {
            Rating rating = this.ratingsService.rateMovie(requestDto.getUserId(), requestDto.getMovieId(), requestDto.getRating());
            responseDto.setRating(rating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | MovieNotFoundException e) {
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public GetAverageMovieResponseDto getAverageMovieRating(GetAverageMovieRequestDto requestDto){

        GetAverageMovieResponseDto responseDto = new GetAverageMovieResponseDto();
        try {
            double rating = this.ratingsService.getAverageRating(requestDto.getMovieId());
            responseDto.setAverageRating(rating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (MovieNotFoundException e) {
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
