package com.restaurant.repositories;

import com.restaurant.models.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(long id);

    User save(User user);
}
