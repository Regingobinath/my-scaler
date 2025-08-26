package com.learn.parkinglot.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.learn.parkinglot.models.ParkingLot;

public class ParkingLotRepositoryImpl implements ParkingLotRepository{

    private Map<Long, ParkingLot> parkingLotStore;
    private Long autoId;
    
    public ParkingLotRepositoryImpl() {
        this.parkingLotStore = new HashMap<>();
        this.autoId = 0l;
    }

    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
        return this.parkingLotStore.values().stream().filter(
            lot -> lot.getGates().stream().filter(
                gate -> gate.getId() == gateId).findFirst().isPresent()).findFirst();
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
        return Optional.ofNullable(this.parkingLotStore.get(id));
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        parkingLot.setId(Optional.ofNullable(parkingLot.getId()).orElseGet(() -> ++autoId));
        this.parkingLotStore.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }

    @Override
    public List<ParkingLot> getAllparkingLots() {
        return this.parkingLotStore.values().stream().toList();
    }

}
