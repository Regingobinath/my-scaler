package com.learn.parkinglot.repositories;

import com.learn.parkinglot.models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class VehicleRepositoryImpl implements VehicleRepository{
    private Map<String, Vehicle> vehicleStore;
    
    public VehicleRepositoryImpl(){
        this.vehicleStore = new HashMap<>();
    }

    @Override
    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
        return Optional.ofNullable(this.vehicleStore.get(registrationNumber));
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        this.vehicleStore.put(vehicle.getRegistrationNumber(), vehicle);
        return vehicle;
    }
    
}
