package com.app.converter;

import com.app.model.car.Car;
import com.google.gson.Gson;

import java.util.List;

public class CarsConverter extends JsonConverter<List<Car>>{
    public CarsConverter(Gson gson) {
        super(gson);
    }
}
