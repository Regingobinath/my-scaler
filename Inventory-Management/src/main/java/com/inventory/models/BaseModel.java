package com.inventory.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
