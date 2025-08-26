package com.myscaler.urlshortner.repositories;

import com.myscaler.urlshortner.models.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenedUrlRepository  extends JpaRepository<ShortenedUrl, Long> {
    public Optional<ShortenedUrl> findByShortUrl(String shortUrl);
}
