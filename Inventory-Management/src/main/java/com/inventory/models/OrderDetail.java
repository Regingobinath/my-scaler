package com.inventory.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class OrderDetail extends BaseModel{
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private int quantity;
}
