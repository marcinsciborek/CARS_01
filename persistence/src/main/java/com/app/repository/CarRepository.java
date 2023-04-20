package com.app.repository;

import com.app.model.car.Car;

import java.util.List;

public interface CarRepository {
    List<Car> findAll();
    Car findById(int id);


}
