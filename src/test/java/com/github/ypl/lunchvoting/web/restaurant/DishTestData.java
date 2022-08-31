package com.github.ypl.lunchvoting.web.restaurant;

import com.github.ypl.lunchvoting.MatcherFactory;
import com.github.ypl.lunchvoting.model.Dish;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public final static int DISH_ID = 1;
    public final static int RESTAURANT_2_DISH_ID = 5;
    public static final int NOT_FOUND = 100;

    public final static Dish dish1 = new Dish(DISH_ID, "restaurant_1 dish_1", new BigDecimal("150.00"), LocalDate.now());
    public final static Dish dish2 = new Dish(DISH_ID + 1, "restaurant_1 dish_2", new BigDecimal("215.35"), LocalDate.now());
    public final static Dish dish3 = new Dish(DISH_ID + 2, "restaurant_1 dish_3", new BigDecimal("115.30"), LocalDate.now());
    public final static Dish dish4 = new Dish(DISH_ID + 3, "restaurant_1 dish_4", new BigDecimal("99.99"), LocalDate.now());

    public final static Dish dish5 = new Dish(DISH_ID + 4, "restaurant_2 dish_1", new BigDecimal("100.00"), LocalDate.now());
    public final static Dish dish6 = new Dish(DISH_ID + 5, "restaurant_2 dish_2", new BigDecimal("120.56"), LocalDate.now());
    public final static Dish dish7 = new Dish(DISH_ID + 6, "restaurant_2 dish_3", new BigDecimal("110.00"), LocalDate.now());

    public final static Dish dish8 = new Dish(DISH_ID + 7, "restaurant_3 dish_1", new BigDecimal("50.00"), LocalDate.of(2022, 5, 26));
    public final static Dish dish9 = new Dish(DISH_ID + 8, "restaurant_3 dish_2", new BigDecimal("80.60"), LocalDate.of(2022, 5, 26));
    public final static Dish dish10 = new Dish(DISH_ID + 9, "restaurant_3 dish_3", new BigDecimal("70.50"), LocalDate.of(2022, 5, 26));

    public static Dish getNew() {
        return new Dish(null, "newDish", new BigDecimal("200.00"), LocalDate.now());
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, "updated", new BigDecimal("500.00"), dish1.getAdded());
    }
}
