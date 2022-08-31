package com.github.ypl.lunchvoting.service;

import com.github.ypl.lunchvoting.model.Dish;
import com.github.ypl.lunchvoting.repository.DishRepository;
import com.github.ypl.lunchvoting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public Dish save(Dish dish, int menuId) {
        dish.setRestaurant(restaurantRepository.getReferenceById(menuId));
        return dishRepository.save(dish);
    }
}
