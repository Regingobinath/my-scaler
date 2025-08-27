package com.myscaler.bms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Feature> features;
    @ManyToOne
    private Screen screen;
}
