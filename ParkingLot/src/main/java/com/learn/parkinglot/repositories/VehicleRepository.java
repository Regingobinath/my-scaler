package com.learn.parkinglot.repositories;

import com.learn.parkinglot.models.Vehicle;

import java.util.Optional;

public interface VehicleRepository {
    // Do not modify the method signatures, feel free to add new methods

    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber);

    public Vehicle save(Vehicle vehicle);
}
