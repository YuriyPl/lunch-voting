package com.github.ypl.lunchvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true, exclude = {"restaurant"})
@Entity
@Table(name = "dishes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "added"}, name = "dish_unique_name_added_idx")})
public class Dish extends AbstractNamedEntity {

    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 8, fraction = 2)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "added", nullable = false, columnDefinition = "date default now()", updatable = false)
    private LocalDate added;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    protected Dish() {
    }

    public Dish(Integer id, String name, BigDecimal price, LocalDate added) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.added = added;
    }
}
