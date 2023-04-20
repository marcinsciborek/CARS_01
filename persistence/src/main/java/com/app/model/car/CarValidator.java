package com.app.model.car;

import com.app.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.app.validator.Validator.*;

public class CarValidator implements Validator<Car> {

    private final String regex;
    private final int limitYear;
    private final int limitMileage;


    public CarValidator(String regex, int limitYear, int limitMileage) {
        this.regex = regex;
        this.limitYear = limitYear;
        this.limitMileage = limitMileage;
    }

    @Override
    public Map<String, String> validate(Car car) {
        var errors = new HashMap<String, String>();

        if (isLowerThan(car.id, 0)) {
            errors.put("ID", "Has to be positive number");
        }

        if (notMatchesRegex(car.brand, regex)) {
            errors.put("Brand", "Is null, empty or not matching regex");
        }

        if (notMatchesRegex(car.model, regex)) {
            errors.put("Model", "Is null, empty or not matching regex");
        }


        if (isLowerOrEqualThan(car.price, BigDecimal.ZERO)) {
            errors.put("Price", "Has to be positive number");
        }

        if (isLowerThan(car.year, limitYear)) {
            errors.put("Year", "Not correct");
        }

        if (isLowerOrEqualThan(car.mileage, 0)) {
            errors.put("Mileage", "Has to be positive");
        }

        if (!car.components.stream().allMatch(c -> c.matches(regex))) {
            errors.put("Component", "Not all matching regex");
        }

        return errors;
    }
}