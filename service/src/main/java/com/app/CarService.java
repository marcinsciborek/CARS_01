package com.app;

import com.app.model.car.Car;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public interface CarService {

    List<Car> getAllCars();
    Car getCarWithId(Integer id);
    List<Car> findBy(Predicate<Car> carTester);

    List<Car> findAllByBrand(String brand, String regex);
    List<Car> findAllByModel(String model, String regex);
    List<Car> findAllByPriceRange(BigDecimal priceFrom, BigDecimal priceTo);
    List<Car> findAllByYearRange(int yearFrom, int yearTo);
    List<Car> findAllByMileageGreaterThan(int limitMileage);
    List<Car> findAllByMileageLowerThan(int limitMileage);
    List<Car> findAllContainingComponent(String component);
    Set<String> findAllComponents();


    List<Car> getMostExpensiveCars();
    List<Car> getAllWithSortedComponents(Comparator<String> componentsComparator);
    Map<String, List<Car>> groupCarsByComponents();

}
