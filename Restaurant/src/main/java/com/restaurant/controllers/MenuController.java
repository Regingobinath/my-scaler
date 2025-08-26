package com.restaurant.controllers;

import com.restaurant.dtos.*;
import com.restaurant.exceptions.InvalidDietaryPreference;
import com.restaurant.exceptions.UnAuthorizedAccess;
import com.restaurant.exceptions.UserNotFoundException;
import com.restaurant.models.MenuItem;
import com.restaurant.services.MenuService;

import java.util.List;

public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    public GetMenuItemsResponseDto getMenuItems(GetMenuItemsRequestDto requestDto){
        GetMenuItemsResponseDto response = new GetMenuItemsResponseDto();
        try {
            List<MenuItem> menuItems = this.menuService.getMenuItems(requestDto.getDietaryRequirement());
            response.setMenuItems(menuItems);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InvalidDietaryPreference e) {
            System.out.println(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }

    public AddMenuItemResponseDto addMenuItem(AddMenuItemRequestDto requestDto){
        AddMenuItemResponseDto response = new AddMenuItemResponseDto();
        try{
            response.setMenuItem(
                    this.menuService.addMenuItem(requestDto.getUserId(), requestDto.getName(),
                    requestDto.getPrice(), requestDto.getDietaryRequirement(),
                    requestDto.getItemType(), requestDto.getDescription()));
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | UnAuthorizedAccess e) {
            System.out.println(e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}
