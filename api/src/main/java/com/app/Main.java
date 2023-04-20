package com.app;

import com.app.converter.CarsConverter;
import com.app.impl.CarServiceImpl;
import com.app.repository.CarRepository;
import com.google.gson.GsonBuilder;

public class  Main {
    public static void main(String[] args) {

        final var FILENAME = "persistence/src/main/resources/cars.json";
        var gson = new GsonBuilder().setPrettyPrinting().create();
        var cars = new CarsConverter(gson)
                .fromJson(FILENAME)
                .orElseThrow(() -> new IllegalStateException("Cannot read from json file"));

        System.out.println(cars);

        var carService = new CarServiceImpl((CarRepository) cars);


    }
}
