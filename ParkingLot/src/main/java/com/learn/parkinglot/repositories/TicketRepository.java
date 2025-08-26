package com.learn.parkinglot.repositories;

import com.learn.parkinglot.models.Ticket;

import java.util.Optional;

public interface TicketRepository {
    // Do not modify the method signature, feel free to add new methods

    public Ticket save(Ticket ticket);

    public Optional<Ticket> getTicketById(long ticketId);
}
