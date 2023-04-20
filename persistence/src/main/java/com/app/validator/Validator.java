package com.app.validator;

import java.math.BigDecimal;
import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T t);

    static boolean notMatchesRegex(String text, String regex) {
        return text == null || regex == null || !text.matches(regex);
    }

    static boolean isLowerThan(int number, int limit) {
        return number < limit;
    }

    static boolean isLowerOrEqualThan(BigDecimal value, BigDecimal limit) {
        return value.compareTo(limit) <= 0;
    }

    static boolean isLowerOrEqualThan(int value, int limit) {
        return value <= limit;
    }

}
