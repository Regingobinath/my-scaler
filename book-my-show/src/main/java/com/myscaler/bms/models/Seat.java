package com.myscaler.bms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Seat extends BaseModel{
    private String name;
    @Enumerated(value = EnumType.STRING)
    SeatType seatType;
    @ManyToOne
    Screen screen;
}
