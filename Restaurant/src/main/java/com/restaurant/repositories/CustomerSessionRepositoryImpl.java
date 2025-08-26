package com.restaurant.repositories;

import com.restaurant.models.CustomerSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{

    private Map<Long, CustomerSession> sessionStore;

    public CustomerSessionRepositoryImpl() {
        this.sessionStore = new HashMap<>();
    }

    @Override
    public CustomerSession save(CustomerSession customerSession) {
        if(customerSession.getId() <= 0) {
            customerSession.setId(RestaurantKeyGenerator.generateKey(this.sessionStore.keySet()));
        }
        this.sessionStore.put(customerSession.getId(), customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return Optional.ofNullable(this.sessionStore.get(userId));
    }
    
}
