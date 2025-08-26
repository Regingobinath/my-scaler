package com.inventory.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
    @ManyToOne
    private Seller seller;
}
