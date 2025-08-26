package com.restaurant.repositories;

import java.util.Set;

public class RestaurantKeyGenerator {

    public static long generateKey(Set<Long> existingKeys){
       return existingKeys.stream().max(Long::compareTo).orElse(0L) + 1;
    }
    
}
