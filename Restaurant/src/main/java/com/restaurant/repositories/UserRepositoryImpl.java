package com.restaurant.repositories;

import com.restaurant.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{

    private final Map<Long, User> userStore;

    public UserRepositoryImpl() {
        this.userStore = new HashMap<>();
    }
    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(this.userStore.get(id));
    }

    @Override
    public User save(User user) {
        if(user.getId() <= 0) {
            user.setId(RestaurantKeyGenerator.generateKey(this.userStore.keySet()));
        }
        this.userStore.put(user.getId(), user);
        return user;
    }
}
