package com.myscaler.urlshortner.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ShortenedUrl extends BaseModel{
    private String originalUrl;
    private String shortUrl;
    private long expiresAt;
    @ManyToOne
    private User user;
}
