package com.github.ypl.lunchvoting.repository;

import com.github.ypl.lunchvoting.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("""
            SELECT r FROM Restaurant r
            JOIN FETCH r.dishes d
            WHERE r.id=:id AND d.restaurant.id=:id AND d.added=CURRENT_DATE
            ORDER BY r.name, r.address""")
    Optional<Restaurant> getWithActiveDishes(int id);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.added=CURRENT_DATE ORDER BY r.name, r.address")
    List<Restaurant> getAllActive();
}
