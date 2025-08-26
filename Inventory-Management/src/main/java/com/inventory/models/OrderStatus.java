package com.inventory.models;

import com.inventory.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public enum OrderStatus {
    PLACED,
    CANCELLED,
    SHIPPED,
    DELIVERED;

    public static List<OrderStatus> getCompletedStatuses() {
        List<OrderStatus> status = new ArrayList<>();
        status.add(OrderStatus.CANCELLED);
        status.add(OrderStatus.DELIVERED);
        status.add(OrderStatus.SHIPPED);

        return status;
    }
}
