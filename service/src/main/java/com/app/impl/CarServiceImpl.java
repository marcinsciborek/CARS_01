package com.app.impl;

import com.app.CarService;
import com.app.impl.exception.CarServiceImplException;
import com.app.model.car.Car;
import com.app.repository.CarRepository;
import com.app.repository.exception.CarRepositoryImplException;
import com.app.validator.Validator;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.app.model.car.CarUtils.*;
import static com.app.validator.Validator.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        System.out.println("---");
        System.out.println(carRepository.findAll());
        System.out.println("---");
        return carRepository.findAll();
    }


    @Override
    public Car getCarWithId(Integer id) {
        if (isLowerOrEqualThan(id, 0)) {
            throw new CarServiceImplException("Id is not positive");
        }
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findBy(Predicate<Car> carTester) {
        if (carTester == null) {
            throw new IllegalArgumentException("Car tester is null");
        }
        return getAllCars()
                .stream()
                .filter(carTester)
                .toList();
    }

    @Override
    public List<Car> findAllByBrand(String brand, String regex) {
        if (Validator.notMatchesRegex(brand, regex)) {
            throw new CarRepositoryImplException("Brand is not correct");
        }
        return findBy(car -> car.hasBrand(brand));
    }

    @Override
    public List<Car> findAllByModel(String model, String regex) {
        if (Validator.notMatchesRegex(model, regex)) {
            throw new CarRepositoryImplException("Model is null or empty");
        }
        return findBy(car -> car.hasModel(model));
    }

    @Override
    public List<Car> findAllByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        if (priceFrom == null || priceTo == null) {
            throw new CarRepositoryImplException("Price range not set");
        }
        if (priceFrom.compareTo(priceTo) > 0) {
            throw new CarRepositoryImplException("Price range is wrong");
        }
        return findBy(car -> car.hasPriceBetween(priceFrom, priceTo));
    }

    @Override
    public List<Car> findAllByYearRange(int yearFrom, int yearTo) {
        if (yearFrom > yearTo) {
            throw new CarRepositoryImplException("Wrong range");
        }
        return findBy(car -> car.hasYearBetween(yearFrom, yearTo));
    }

    @Override
    public List<Car> findAllByMileageGreaterThan(int limitMileage) {
        return findBy(car -> car.hasMileageGreaterThan(limitMileage));
    }

    @Override
    public List<Car> findAllByMileageLowerThan(int limitMileage) {
        return findBy(car -> car.hasMileageLowerThan(limitMileage));
    }

    @Override
    public List<Car> findAllContainingComponent(String component) {
        return findBy(car -> car.hasComponent(component));
    }

    @Override
    public Set<String> findAllComponents() {
        return getAllCars()
                .stream()
                .flatMap(car -> toComponents.apply(car).stream())
                .collect(Collectors.toSet());
    }

    @Override
    public List<Car> getMostExpensiveCars() {
        return carRepository
                .findAll()
                .stream()
                .collect(groupingBy(toPrice))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new CarServiceImplException("Cannot find most expensive cars"));
    }

    @Override
    public List<Car> getAllWithSortedComponents(Comparator<String> componentsComparator) {
        if (componentsComparator == null) {
            throw new CarServiceImplException("Components comparator is null");
        }

        return carRepository
                .findAll()
                .stream()
                .map(car -> car.withSortedComponents(componentsComparator))
                .toList();
    }

    @Override
    public Map<String, List<Car>> groupCarsByComponents() {
        return findAllComponents()
                .stream()
                .collect(toMap(
                        identity(),
                        component -> findBy(car -> car.hasComponent(component))
                ));
    }

    @Override
    public String toString() {
        return "CARS " + carRepository.findAll();
    }

}
