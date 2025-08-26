package com.restaurant.services;

import com.restaurant.exceptions.InvalidDietaryPreference;
import com.restaurant.exceptions.UnAuthorizedAccess;
import com.restaurant.exceptions.UserNotFoundException;
import com.restaurant.models.*;
import com.restaurant.repositories.MenuItemRepository;
import com.restaurant.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuServiceImpl implements MenuService {
    private final UserRepository userRepo;
    private final MenuItemRepository menuItemRepo;

    public MenuServiceImpl(UserRepository userRepo, MenuItemRepository menuItemRepo) {
        this.userRepo = userRepo;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public List<MenuItem> getMenuItems(String itemType) throws InvalidDietaryPreference{
        DietaryRequirement dietary = null;
        if (!Objects.isNull(itemType)) {
            dietary = DietaryRequirement.valueOf(itemType);
            if(Objects.isNull(dietary)) {
                throw new InvalidDietaryPreference("Invalid dietary preference");
            }
        }
        if (Objects.isNull(dietary)) {
            return this.menuItemRepo.getAll();
        } else {
            return this.menuItemRepo.getByDietaryRequirement(dietary);
        }
    }

    @Override
    public MenuItem addMenuItem(
            long userId, String name, double price,
            String dietaryRequirement, String itemType,
            String description) throws UserNotFoundException, UnAuthorizedAccess {
        Optional<User> user = this.userRepo.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (!user.get().getUserType().equals(UserType.ADMIN)) {
            throw new UnAuthorizedAccess("UnAuthorized access");
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setDietaryRequirement(DietaryRequirement.valueOf(dietaryRequirement));
        menuItem.setItemType(ItemType.valueOf(itemType));
        menuItem.setPrice(price);

        this.menuItemRepo.save(menuItem);
        return menuItem;
    }
}
