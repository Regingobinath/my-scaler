package com.myscaler.urlshortner.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "url_user")
public class User extends BaseModel{
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserPlan userPlan;
}
