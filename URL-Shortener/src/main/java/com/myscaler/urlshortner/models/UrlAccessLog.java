package com.myscaler.urlshortner.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UrlAccessLog extends BaseModel{
    @ManyToOne
    private ShortenedUrl shortenedUrl;
    private long accessedAt;
}
