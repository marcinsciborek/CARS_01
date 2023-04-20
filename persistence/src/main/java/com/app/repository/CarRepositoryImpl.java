package com.app.repository;

import com.app.converter.JsonConverter;
import com.app.model.car.Car;
import com.app.model.car.CarUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;


@Service
public class CarRepositoryImpl implements CarRepository {
    private final Map<Integer, Car> cars;

    public CarRepositoryImpl(JsonConverter<List<Car>> carsConverter, String filename) {
        this.cars = carsConverter
                .fromJson(filename)
                .orElseThrow(() -> new IllegalStateException("Cannot parse from json file"))
                .stream()
                .collect(toMap(
                        CarUtils.toId,
                        identity()
                ));
    }

    @Override
    public List<Car> findAll() {
        return cars
                .values()
                .stream()
                .toList();
    }

    @Override
    public Car findById(int id) {
        var car = cars.get(id);
        if (car == null) {
            throw new IllegalStateException("Car not found");
        }
        return car;
    }


}
