package com.inventory.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Preference extends BaseModel{
    private String category;
    private String description;
    private Date createdAt;
}
