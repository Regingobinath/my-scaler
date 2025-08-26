package com.restaurant.repositories;

import com.restaurant.models.DietaryRequirement;
import com.restaurant.models.MenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MenuItemRepositoryImpl implements  MenuItemRepository{

    private final Map<Long, MenuItem> menuStore;

    public MenuItemRepositoryImpl() {
        this.menuStore = new HashMap<>();
    }

    @Override
    public MenuItem add(MenuItem menuItem) {
        if(menuItem.getId() <= 0) {
            menuItem.setId(RestaurantKeyGenerator.generateKey(this.menuStore.keySet()));
        }
        this.menuStore.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        if(menuItem.getId() <= 0) {
            menuItem.setId(RestaurantKeyGenerator.generateKey(this.menuStore.keySet()));
        }
        this.menuStore.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @Override
    public Optional<MenuItem> findById(long id) {
        return Optional.ofNullable(this.menuStore.get(id));
    }

    @Override
    public List<MenuItem> getAll() {
        return this.menuStore.values().stream().toList();
    }

    @Override
    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        return this.menuStore.values().stream().filter(
                item -> item.getDietaryRequirement().equals(dietaryRequirement)).toList();
    }

}
