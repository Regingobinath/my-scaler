package com.myscaler.bms.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Theatre extends BaseModel{

    private String name;
    private String address;
    @OneToMany(mappedBy = "theatre")
    private List<Screen> screens;
    @ManyToOne
    private City city;

}
