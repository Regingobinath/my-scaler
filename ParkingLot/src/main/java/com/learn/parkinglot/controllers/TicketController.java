package com.learn.parkinglot.controllers;

import com.learn.parkinglot.dtos.GenerateTicketRequestDto;
import com.learn.parkinglot.dtos.GenerateTicketResponseDto;
import com.learn.parkinglot.dtos.ResponseStatus;
import com.learn.parkinglot.exceptions.*;
import com.learn.parkinglot.models.ParkingLot;
import com.learn.parkinglot.models.Ticket;
import com.learn.parkinglot.repositories.ParkingLotRepository;
import com.learn.parkinglot.services.TicketService;

public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        GenerateTicketResponseDto generateTicketResponseDto = new GenerateTicketResponseDto();
        try {
            Ticket ticket = ticketService.generateTicket(
                    requestDto.getGateId(),
                    requestDto.getRegistrationNumber() ,
                    requestDto.getVehicleType(),
                    requestDto.getAdditionalServices());

            generateTicketResponseDto.setTicket(ticket);
            generateTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (InvalidGateException | InvalidParkingLotException | ParkingSpotNotAvailableException
                 | UnsupportedAdditionalService | AdditionalServiceNotSupportedByVehicle e) {
            System.out.println(e.getMessage());
            generateTicketResponseDto.setTicket(null);
            generateTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return generateTicketResponseDto;
    }
}
