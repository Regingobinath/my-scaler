package com.myscaler.bms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Ticket extends BaseModel{

    @ManyToOne
    private Show show;
    @OneToMany
    private List<Seat> seats;
    private Date timeOfBooking;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private TicketStatus status;
}
