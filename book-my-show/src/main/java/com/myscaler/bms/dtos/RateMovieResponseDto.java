package com.myscaler.bms.dtos;

import com.myscaler.bms.models.Rating;
import lombok.Data;

@Data
public class RateMovieResponseDto {
    private ResponseStatus responseStatus;
    private Rating rating;
}
