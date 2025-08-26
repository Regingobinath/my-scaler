package com.inventory.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.mapping.ToOne;

@Data
@Entity
public class Seller extends BaseModel{
    private String name;
    private String email;
    @OneToOne
    private Address address;
}
