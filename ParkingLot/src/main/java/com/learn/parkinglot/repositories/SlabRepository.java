package com.learn.parkinglot.repositories;

import com.learn.parkinglot.models.Slab;
import com.learn.parkinglot.models.VehicleType;

import java.util.List;

public interface SlabRepository {

    public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType);

    public Slab save(Slab slab);
}
