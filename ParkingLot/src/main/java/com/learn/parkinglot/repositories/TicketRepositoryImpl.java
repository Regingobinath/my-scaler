package com.learn.parkinglot.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.learn.parkinglot.models.Ticket;

public class TicketRepositoryImpl implements TicketRepository{
    
    private Map<Long, Ticket> ticketStore;
    private Long autoId;

    public TicketRepositoryImpl() {
        this.ticketStore = new HashMap<>();
        this.autoId = 0l;
    }

    @Override
    public Ticket save(Ticket ticket) {
        ticket.setId(Optional.ofNullable(ticket.getId()).orElseGet(() -> ++autoId));
        this.ticketStore.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> getTicketById(long ticketId) {
        return Optional.ofNullable(this.ticketStore.get(ticketId));
    }
    
}
