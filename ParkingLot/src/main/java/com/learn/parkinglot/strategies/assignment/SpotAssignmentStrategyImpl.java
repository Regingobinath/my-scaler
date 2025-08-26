package com.learn.parkinglot.strategies.assignment;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.learn.parkinglot.models.*;

public class SpotAssignmentStrategyImpl implements SpotAssignmentStrategy{

    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        List<ParkingFloor> parkingFloors = this.fetchOperationalFloors(parkingLot.getParkingFloors());
        if(Objects.isNull(parkingFloors) || parkingFloors.size() == 0){
            return Optional.empty();
        }
        //get parking floor and its capacities filter by vehicle type and available spots
        Map<ParkingFloor, Integer> floorCapacities = this.getFloorCapacities(parkingFloors, vehicleType);
        if(Objects.isNull(floorCapacities) || floorCapacities.isEmpty()){
            return Optional.empty();
        }

        //find least capacity 
        int minCapacity = floorCapacities.values().stream().filter(spotCount -> spotCount > 0).min(Integer::compareTo).orElse(0);

        //find floors with least capacity(same capacity)
        List<ParkingFloor> leastCapacityFloors = 
            floorCapacities.keySet().stream().filter(
                floor -> (int)floorCapacities.get(floor) == minCapacity).toList();

        //find nearest floor (min floor number)
        ParkingFloor nearestFloor = leastCapacityFloors.size() == 1 ? leastCapacityFloors.get(0) :
            leastCapacityFloors.stream().min(Comparator.comparingInt(ParkingFloor::getFloorNumber)).orElse(null);

        if(Objects.isNull(nearestFloor) || Objects.isNull(nearestFloor.getSpots()) || nearestFloor.getSpots().size() == 0){
            return Optional.empty();
        }

        //get spots with available status
        List<ParkingSpot> availableParkingSpots = this.getAvailableSlots(nearestFloor.getSpots());
        if(Objects.isNull(availableParkingSpots) || availableParkingSpots.isEmpty()){
            return Optional.empty();
        }

        //get nearest spot (min spot number) 
        ParkingSpot nearestParkingSpot = this.getNearestParkingSpot(availableParkingSpots, vehicleType);
        if(Objects.isNull(nearestParkingSpot) == false) {
            nearestParkingSpot = this.setParkingSpotClosed(nearestParkingSpot);
        }
        
        return Optional.ofNullable(nearestParkingSpot);
    }

    private List<ParkingFloor> fetchOperationalFloors(List<ParkingFloor> parkingFloors){

        return parkingFloors.stream()
            .filter(parkingFloor -> parkingFloor.getStatus().equals(FloorStatus.OPERATIONAL)).toList();
    }

    private List<ParkingSpot> getAvailableSlots(List<ParkingSpot> parkingSpots){
        return parkingSpots.stream()
            .filter(parkingSpot -> parkingSpot.getStatus().equals(ParkingSpotStatus.AVAILABLE)).toList();
    }

    private Map<ParkingFloor, Integer> getFloorCapacities(List<ParkingFloor> parkingFloors, VehicleType vehicleType){

        Map<ParkingFloor, Integer> parkinFloorVsCapacity;
        parkinFloorVsCapacity = parkingFloors.stream().collect(Collectors.toMap(
            parkingFloor -> parkingFloor,
            (parkingFloor) -> getAvailableSlots(parkingFloor.getSpots()).stream().filter(
                spot -> spot.getSupportedVehicleType().equals(vehicleType))
                .toList().size()));

        return parkinFloorVsCapacity;
    }

    private ParkingSpot setParkingSpotClosed(ParkingSpot parkingSpot) {
        parkingSpot.setStatus(ParkingSpotStatus.CLOSED);
        return parkingSpot;
    }

    private ParkingSpot getNearestParkingSpot(List<ParkingSpot> availableParkingSpots, VehicleType vehicleType){
        return availableParkingSpots.stream()
            .filter(spot -> spot.getSupportedVehicleType().equals(vehicleType) 
                && spot.getStatus().equals(ParkingSpotStatus.AVAILABLE))
                    .min(Comparator.comparingInt(spot -> spot.getNumber())).orElse(null);
    }
    
}
