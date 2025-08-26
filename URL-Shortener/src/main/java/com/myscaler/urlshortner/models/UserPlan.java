package com.myscaler.urlshortner.models;

import jakarta.persistence.Entity;
import lombok.Getter;

public enum UserPlan {
    FREE,
    TEAM,
    BUSINESS,
    ENTERPRISE
}
