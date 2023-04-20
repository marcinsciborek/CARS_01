package com.app.model.car;


import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public interface CarUtils {
    Function<Car, List<String>> toComponents = car -> car.components;
    Function<Car, Integer> toId = car -> car.id;
    Function<Car, BigDecimal> toPrice = car -> car.price;

}
