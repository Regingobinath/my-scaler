package com.learn.parkinglot.strategies.assignment;

import com.learn.parkinglot.models.ParkingLot;
import com.learn.parkinglot.models.ParkingSpot;
import com.learn.parkinglot.models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {

    Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType);

}
