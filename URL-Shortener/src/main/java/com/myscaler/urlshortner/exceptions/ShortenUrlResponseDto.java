package com.myscaler.urlshortner.exceptions;

import com.myscaler.urlshortner.dtos.ResponseStatus;
import lombok.Data;

@Data
public class ShortenUrlResponseDto {
    private String shortUrl;
    private long expiresAt;
    private ResponseStatus status;
}
