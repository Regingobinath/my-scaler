package com.myscaler.bms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bms_user")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
