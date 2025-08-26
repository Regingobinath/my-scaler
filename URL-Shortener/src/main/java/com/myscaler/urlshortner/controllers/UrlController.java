package com.myscaler.urlshortner.controllers;

import com.myscaler.urlshortner.dtos.ResolveShortenUrlRequestDto;
import com.myscaler.urlshortner.dtos.ResolveShortenUrlResponseDto;
import com.myscaler.urlshortner.dtos.ResponseStatus;
import com.myscaler.urlshortner.dtos.ShortenUrlRequestDto;
import com.myscaler.urlshortner.exceptions.ShortenUrlResponseDto;
import com.myscaler.urlshortner.models.ShortenedUrl;
import com.myscaler.urlshortner.services.UrlService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    public ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto requestDto) {
        ShortenUrlResponseDto responseDto = new ShortenUrlResponseDto();
        try {
            ShortenedUrl shortenedUrl =
                    urlService.shortenUrl(requestDto.getOriginalUrl(), requestDto.getUserId());
            responseDto.setShortUrl(shortenedUrl.getShortUrl());
            responseDto.setExpiresAt(shortenedUrl.getExpiresAt());
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public ResolveShortenUrlResponseDto resolveShortenedUrl(ResolveShortenUrlRequestDto requestDto) {
        ResolveShortenUrlResponseDto responseDto = new ResolveShortenUrlResponseDto();
        try {
            responseDto.setOriginalUrl(
                    this.urlService.resolveShortenedUrl(requestDto.getShortenUrl()));
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            responseDto.setStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
