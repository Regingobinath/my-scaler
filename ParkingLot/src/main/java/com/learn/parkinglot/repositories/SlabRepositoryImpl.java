package com.learn.parkinglot.repositories;

import com.learn.parkinglot.models.Slab;
import com.learn.parkinglot.models.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SlabRepositoryImpl implements SlabRepository{

    private Map<Long, Slab> slabStore;
    private Long autoId;

    public SlabRepositoryImpl() {
        this.slabStore = new HashMap<>();
        this.autoId = 0l;
    }

    @Override
    public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType) {
        return this.slabStore.values().stream().filter(
            slab -> slab.getVehicleType().equals(vehicleType)).toList();
    }

    @Override
    public Slab save(Slab slab) {
        slab.setId(slab.getId() > 0 ? slab.getId(): ++ autoId);
        this.slabStore.put(slab.getId(), slab);
        return slab;
    }
    
}
