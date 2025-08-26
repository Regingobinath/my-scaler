package com.learn.parkinglot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.learn.parkinglot.exceptions.*;
import com.learn.parkinglot.models.*;
import com.learn.parkinglot.repositories.GateRepository;
import com.learn.parkinglot.repositories.ParkingLotRepository;
import com.learn.parkinglot.repositories.TicketRepository;
import com.learn.parkinglot.repositories.VehicleRepository;
import com.learn.parkinglot.strategies.assignment.SpotAssignmentStrategy;
import com.learn.parkinglot.strategies.assignment.SpotAssignmentStrategyImpl;

public class TicketServiceImpl implements TicketService{
    
    private ParkingLotRepository parkingLotRepository;
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private TicketRepository ticketRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    
    public TicketServiceImpl(
        ParkingLotRepository parkingLotRepository, 
        GateRepository gateRepository, 
        VehicleRepository vehicleRepository,
        TicketRepository ticketRepository,
        SpotAssignmentStrategyImpl spotAssignmentStrategy) {

        this.parkingLotRepository = parkingLotRepository;
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.spotAssignmentStrategy = new SpotAssignmentStrategyImpl();
    }

    @Override
    public Ticket generateTicket(
            int gateId, String registrationNumber, String vehicleType, List<String> additionalServices)
        throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException, UnsupportedAdditionalService, AdditionalServiceNotSupportedByVehicle {
        Optional<Gate> gate = gateRepository.findById(gateId);

        validateGate(gate);

        Vehicle vehicle = checkAndSaveVehicle(registrationNumber, vehicleType);
        Optional<ParkingLot> pLotOptional = this.parkingLotRepository.getParkingLotByGateId(gateId);

        if(pLotOptional.isEmpty()) {
            throw new InvalidParkingLotException("Parking Lot Not Found");
        }

        List<AdditionalService> additionalServiceObjs = new ArrayList<>();
        if(additionalServices != null) {
            for (String additionalServiceStr : additionalServices) {
                AdditionalService additionalService;
                try {
                    additionalService = AdditionalService.valueOf(additionalServiceStr);
                } catch (IllegalArgumentException e) {
                    throw new UnsupportedAdditionalService("Invalid additional service. plz check the notice board.. ");
                }
                if (!additionalService.getSupportedVehicleTypes().contains(VehicleType.valueOf(vehicleType))) {
                    throw new AdditionalServiceNotSupportedByVehicle("Invalid vehicle type for additional service");
                }
                additionalServiceObjs.add(additionalService);
            }
        }

        Optional<ParkingSpot> pOptional = spotAssignmentStrategy.assignSpot(
            pLotOptional.get(), VehicleType.valueOf(vehicleType));
        
        if(Objects.isNull(pOptional) || pOptional.isEmpty()){
            throw new ParkingSpotNotAvailableException("Parking Spot Not Available");
        }
        this.parkingLotRepository.save(pLotOptional.get());

        Ticket ticket = new Ticket();
        this.populateTicketDetails(ticket, gate.get(), pOptional.get(), vehicle, additionalServiceObjs);

        this.ticketRepository.save(ticket);

        return ticket;
    }

    private void validateGate(Optional<Gate> gOptional) throws InvalidGateException{
        if(gOptional.isEmpty() || gOptional.get().getType().equals(GateType.EXIT)){
            throw new InvalidGateException("Gate Is Invalid");
        }
    }

    private Vehicle checkAndSaveVehicle(String registrationNumber, String vehicleType){

        Optional<Vehicle> vOptional = vehicleRepository.getVehicleByRegistrationNumber(registrationNumber) ;

        if(vOptional.isPresent()){
            return vOptional.get();
        }

        Vehicle vehicleDto = new Vehicle();

        vehicleDto.setRegistrationNumber(registrationNumber);
        vehicleDto.setVehicleType(VehicleType.valueOf(vehicleType));
        vehicleRepository.save(vehicleDto);

        return vehicleDto;
    }

    private void populateTicketDetails(
            Ticket ticket,
            Gate gate,
            ParkingSpot parkingSpot,
            Vehicle vehicle,
            List<AdditionalService> additionalServices){
        ticket.setParkingSpot(parkingSpot);
        ticket.setParkingAttendant(gate.getParkingAttendant());
        ticket.setVehicle(vehicle);
        ticket.setAdditionalServices(additionalServices);
    }

    public ParkingLotRepository getParkingLotRepository() {
        return this.parkingLotRepository;
    }
}
