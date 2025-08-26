package com.inventory.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "inv_user")
public class User extends BaseModel{
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToMany
    private List<Address> addresses;
    @OneToMany
    private List<Order> orders;
}
