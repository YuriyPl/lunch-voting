package com.github.ypl.lunchvoting.web.restaurant;

import com.github.ypl.lunchvoting.MatcherFactory;
import com.github.ypl.lunchvoting.model.Restaurant;

import java.util.List;

import static com.github.ypl.lunchvoting.web.restaurant.DishTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class);
    public static final MatcherFactory.Matcher<Restaurant> SIMPLE_RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");

    public static final int RESTAURANT_1_ID = 1;
    public static final int RESTAURANT_2_ID = 2;
    public static final int RESTAURANT_3_ID = 3;
    public static final int NOT_FOUND = 10;

    public static Restaurant restaurant1 = new Restaurant(RESTAURANT_1_ID, "restaurant_1", "description_1", "address_1");
    public static Restaurant restaurant2 = new Restaurant(RESTAURANT_2_ID, "restaurant_2", "description_2", "address_2");
    public static Restaurant restaurant3 = new Restaurant(RESTAURANT_3_ID, "restaurant_3", "description_3", "address_3");

    static {
        restaurant1.setDishes(List.of(dish1, dish2, dish3, dish4));
        restaurant2.setDishes(List.of(dish5, dish6, dish7));
        restaurant3.setDishes(List.of(dish8, dish9, dish10));
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "new", "newDesc", "newAddress");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_1_ID, "updated", "updatedDesc", "updatedAddress");
    }
}