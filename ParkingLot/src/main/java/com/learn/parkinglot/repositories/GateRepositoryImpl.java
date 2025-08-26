package com.learn.parkinglot.repositories;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.learn.parkinglot.models.Gate;

public class GateRepositoryImpl implements GateRepository{

    private Map<Long, Gate> gateStore;
    private Long autoId;
    
    public GateRepositoryImpl(){
        this.gateStore = new HashMap<>();
        this.autoId = 0l;
    }

    @Override
    public Optional<Gate> findById(long gateId) {
        return Optional.ofNullable(this.gateStore.get(gateId));
    }

    @Override
    public Gate save(Gate gate) {
        gate.setId(Optional.ofNullable(gate.getId()).orElseGet(() -> ++ autoId));
        this.gateStore.put(gate.getId(), gate);
        return gate;
    }
    
}
