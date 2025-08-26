package com.myscaler.urlshortner.services;


import com.myscaler.urlshortner.exceptions.UrlNotFoundException;
import com.myscaler.urlshortner.exceptions.UserNotFoundException;
import com.myscaler.urlshortner.models.ShortenedUrl;

public interface UrlService {
    public ShortenedUrl shortenUrl(String url, int userId) throws UserNotFoundException;

    public String resolveShortenedUrl(String shortUrl) throws UrlNotFoundException;
}
