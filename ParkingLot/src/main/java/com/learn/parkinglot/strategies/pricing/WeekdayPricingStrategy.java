package com.learn.parkinglot.strategies.pricing;

import com.learn.parkinglot.models.VehicleType;
import com.learn.parkinglot.utils.DateTimeUtils;

import java.util.Date;

public class WeekdayPricingStrategy implements PricingStrategy{

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        int hours = DateTimeUtils.calculateHours(entryTime, exitTime);
        return hours * 10;
    }
}
