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
public class SeatTypeShow extends BaseModel{
    @ManyToOne
    private Show show;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private double price;
}
