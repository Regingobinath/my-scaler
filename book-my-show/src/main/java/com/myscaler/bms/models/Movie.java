package com.myscaler.bms.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Movie extends BaseModel{
    private String name;
    private String description;
}
