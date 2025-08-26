package com.learn.parkinglot.utils;

import com.learn.parkinglot.models.*;

public class ParkingFloorUtils {
    public static int calculateAvailableSpotsByVehicleType(ParkingFloor parkingFloor, VehicleType vehicleType){
        int count = 0;
        if(parkingFloor == null || vehicleType == null || !parkingFloor.getStatus().equals(FloorStatus.OPERATIONAL)){
            return count;
        }
    
        for (ParkingSpot spot : parkingFloor.getSpots()) {
            if(spot.getSupportedVehicleType().equals(vehicleType) && spot.getStatus().equals(ParkingSpotStatus.AVAILABLE)){
                count++;
            }
        }
        return count;
    }
    
}
