package com.learn.parkinglot.strategies.pricing;

import com.learn.parkinglot.models.VehicleType;

import java.util.Date;

public interface PricingStrategy {
    double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType);
}
