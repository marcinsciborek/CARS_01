package com.app.model.car;

import com.app.model.car.color.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class Car {
    Integer id;
    String brand;
    String model;
    BigDecimal price;
    int year;
    Color color;
    int mileage;

    @Builder.Default
    List<String> components = new ArrayList<>();

    public boolean hasId(Integer id) {
        return this.id.equals(id);
    }

    public boolean hasBrand(String brand) {
        return this.brand.equals(brand);
    }
    public boolean hasModel(String model) {
        return this.model.equals(model);
    }

    public boolean hasPriceBetween(BigDecimal from, BigDecimal to) {
        return from.compareTo(price) <= 0 && to.compareTo(price) >= 0;
    }

    public boolean hasYearBetween(int from, int to) {
        return from <= year && to >= year;
    }

    public boolean hasMileageGreaterThan(int mileage) {
        return this.mileage > mileage;
    }
    public boolean hasMileageLowerThan(int mileage) {
        return this.mileage < mileage;
    }

    public boolean hasComponent(String component) {
        return components.contains(component);
    }

    public Car withSortedComponents(Comparator<String> componentsComparator) {
        return Car
                .builder()
                .id(id)
                .model(model)
                .color(color)
                .mileage(mileage)
                .price(price)
                .brand(brand)
                .year(year)
                .components(components
                        .stream()
                        .sorted(componentsComparator)
                        .toList())
                .build();
    }

    @Override
    public String toString() {
        return """
                ********************
                ID:         %s
                BRAND:      %s
                MODEL:      %s
                PRICE:      %s
                YEAR:       %s
                COLOR:      %s
                MILEAGE:    %s
                COMPONENTS: %s
                """.formatted(id, brand, model, price, year, color, mileage, components.stream().collect(Collectors.joining(", ")));
    }
}