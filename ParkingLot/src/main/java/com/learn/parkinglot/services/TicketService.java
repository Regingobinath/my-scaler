package com.learn.parkinglot.services;

import com.learn.parkinglot.exceptions.*;
import com.learn.parkinglot.models.Ticket;
import com.learn.parkinglot.repositories.ParkingLotRepository;

import java.util.List;

public interface TicketService {
    // Do not modify the method signatures, feel free to add new methods
    public Ticket generateTicket(
            int gateId, String registrationNumber, String vehicleType, List<String> additionalServices)
            throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException, UnsupportedAdditionalService, AdditionalServiceNotSupportedByVehicle;
    public ParkingLotRepository getParkingLotRepository();
}
