package com.restaurant.repositories;

import com.restaurant.models.DietaryRequirement;
import com.restaurant.models.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository {

    MenuItem add(MenuItem menuItem);

    Optional<MenuItem> findById(long id);

    List<MenuItem> getAll();

    List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement);

    MenuItem save(MenuItem menuItem);
}
