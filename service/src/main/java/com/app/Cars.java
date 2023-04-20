package com.app;

import com.app.model.car.Car;
import com.app.model.car.color.Color;

import java.math.BigDecimal;
import java.util.List;

public interface Cars {
    Car CAR_1_AUDI = Car.builder()
            .id(1)
            .brand("AUDI")
            .model("Q8")
            .price(BigDecimal.valueOf(250000))
            .year(2020)
            .color(Color.BLACK)
            .mileage(1000)
            .components(List.of("ABS", "ESP"))
            .build();
}
