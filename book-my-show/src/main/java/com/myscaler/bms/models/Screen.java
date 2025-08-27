package com.myscaler.bms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Screen extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    @Enumerated(value = EnumType.STRING)
    private ScreenStatus status;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Feature> features;
    @ManyToOne
    private Theatre theatre;
}
