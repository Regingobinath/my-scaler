package com.myscaler.urlshortner.services;

import com.myscaler.urlshortner.UrlShortenerApplication;
import com.myscaler.urlshortner.exceptions.UrlNotFoundException;
import com.myscaler.urlshortner.exceptions.UserNotFoundException;
import com.myscaler.urlshortner.models.ShortenedUrl;
import com.myscaler.urlshortner.models.UrlAccessLog;
import com.myscaler.urlshortner.models.User;
import com.myscaler.urlshortner.models.UserPlan;
import com.myscaler.urlshortner.repositories.ShortenedUrlRepository;
import com.myscaler.urlshortner.repositories.UrlAccessLogRepository;
import com.myscaler.urlshortner.repositories.UserRepository;
import com.myscaler.urlshortner.utils.ShortUrlGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UrlServiceImpl implements UrlService {

    private UserRepository userRepository;
    private ShortenedUrlRepository shortenedUrlRepository;
    private UrlAccessLogRepository urlAccessLogRepository;

    public UrlServiceImpl(UserRepository userRepository, ShortenedUrlRepository shortenedUrlRepository, UrlAccessLogRepository urlAccessLogRepository) {
        this.userRepository = userRepository;
        this.shortenedUrlRepository = shortenedUrlRepository;
        this.urlAccessLogRepository = urlAccessLogRepository;
    }

    @Override

    public ShortenedUrl shortenUrl(String url, int userId) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById((long) userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }

        int day = switch (userOptional.get().getUserPlan()) {
            case FREE -> 1;
            case TEAM -> 7;
            case BUSINESS -> 30;
            case ENTERPRISE -> 365;
        };

        long planTime = day * 24L * 60 * 60 * 1000;
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setOriginalUrl(url);
        shortenedUrl.setShortUrl(ShortUrlGenerator.generateShortUrl());
        shortenedUrl.setExpiresAt(System.currentTimeMillis() + planTime);
        shortenedUrl.setUser(userOptional.get());

        return shortenedUrlRepository.save(shortenedUrl);
    }

    @Override

    public String resolveShortenedUrl(String shortUrl) throws UrlNotFoundException {

        Optional<ShortenedUrl> urlOptional = shortenedUrlRepository.findByShortUrl(shortUrl);
        if (urlOptional.isEmpty()) {
            throw new UrlNotFoundException("Url not found!");
        }

        ShortenedUrl registeredUrl = urlOptional.get();
        if (registeredUrl.getExpiresAt() < System.currentTimeMillis()) {
            throw new UrlNotFoundException("Url has expired!");
        }

        UrlAccessLog urlAccessLog = new UrlAccessLog();
        urlAccessLog.setShortenedUrl(registeredUrl);
        urlAccessLog.setAccessedAt(System.currentTimeMillis());
        urlAccessLogRepository.save(urlAccessLog);

        return registeredUrl.getOriginalUrl();

    }

}
