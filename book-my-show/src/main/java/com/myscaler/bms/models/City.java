package com.myscaler.bms.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class City extends BaseModel {
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Theatre> theatres;
}
