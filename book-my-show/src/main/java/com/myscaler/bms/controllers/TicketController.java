package com.myscaler.bms.controllers;

import com.myscaler.bms.dtos.BookTicketRequestDTO;
import com.myscaler.bms.dtos.BookTicketResponseDTO;
import com.myscaler.bms.dtos.ResponseStatus;
import com.myscaler.bms.models.Ticket;
import com.myscaler.bms.repositories.TicketRepository;
import com.myscaler.bms.services.TicketService;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO requestDTO){
        BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();
        try {
            Ticket ticket = this.ticketService.bookTicket(requestDTO.getShowSeatIds(), requestDTO.getUserId());
            responseDTO.setTicket(ticket);
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

}
