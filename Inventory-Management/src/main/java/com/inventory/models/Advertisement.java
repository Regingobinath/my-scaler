package com.inventory.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Advertisement extends BaseModel{
    private String name;
    private String description;
    @OneToOne
    private Preference preference;
}
