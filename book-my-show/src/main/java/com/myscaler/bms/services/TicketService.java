package com.myscaler.bms.services;


import com.myscaler.bms.models.Ticket;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws Exception;

}
