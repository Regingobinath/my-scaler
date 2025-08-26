package com.learn.parkinglot.models;

public class ParkingFloorWithEmptySpotCountForVehicleType {
    private ParkingFloor parkingFloor;
    private int spotCount;

    public ParkingFloorWithEmptySpotCountForVehicleType(){

    }
    
    public ParkingFloorWithEmptySpotCountForVehicleType(ParkingFloor parkingFloor, int spotCount) {
        this.parkingFloor = parkingFloor;
        this.spotCount = spotCount;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public int getSpotCount() {
        return spotCount;
    }

    public void setSpotCount(int spotCount) {
        this.spotCount = spotCount;
    }

    
}
