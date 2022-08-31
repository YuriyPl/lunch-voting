package com.github.ypl.lunchvoting.web.restaurant;

import com.github.ypl.lunchvoting.model.Restaurant;
import com.github.ypl.lunchvoting.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.getWithActiveDishes(id));
    }

    public List<Restaurant> getAllActive() {
        log.info("getAllActual");
        return repository.getAllActive();
    }
}
