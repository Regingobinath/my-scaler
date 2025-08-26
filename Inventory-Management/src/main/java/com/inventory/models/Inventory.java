package com.inventory.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="inv_inventory")
public class Inventory extends BaseModel{
    @ManyToOne
    private Product product;
    private int quantity;
}
