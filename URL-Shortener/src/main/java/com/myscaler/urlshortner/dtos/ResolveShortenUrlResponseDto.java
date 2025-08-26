package com.myscaler.urlshortner.dtos;

import lombok.Data;

@Data
public class ResolveShortenUrlResponseDto {
    private String originalUrl;
    private ResponseStatus status;
}
