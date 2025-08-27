package com.myscaler.bms.dtos;

import com.myscaler.bms.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDTO {
    private ResponseStatus status;
    private Ticket ticket;
}
