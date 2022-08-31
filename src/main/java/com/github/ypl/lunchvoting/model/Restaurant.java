package com.github.ypl.lunchvoting.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "restaurants",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurant_unique_name_address_idx")})
public class Restaurant extends AbstractNamedEntity {

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private List<Dish> dishes;

    protected Restaurant() {
    }

    public Restaurant(Integer id, String name, String description, String address) {
        this(id, name, description, address, Collections.emptyList());
    }

    public Restaurant(Integer id, String name, String description, String address, List<Dish> dishes) {
        super(id, name);
        this.name = name;
        this.description = description;
        this.address = address;
        this.dishes = dishes;
    }
}
