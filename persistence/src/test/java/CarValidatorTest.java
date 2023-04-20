import com.app.model.car.Car;
import com.app.model.car.CarValidator;
import com.app.model.car.color.Color;
import extensions.Extension_ParameterResolverCarValidator;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import java.math.BigDecimal;
import java.util.List;

@ExtendWith(Extension_ParameterResolverCarValidator.class)
@RequiredArgsConstructor
class CarValidatorTest {
    private final CarValidator carValidator;


    @Test
    @DisplayName("when car is valid")
    void test1(){
        var car = Car.builder().id(1).brand("FIAT").model("PUNTO").price(BigDecimal.valueOf(50000L)).year(2011)
                .color(Color.RED).mileage(50000).components(List.of("ABS","ESP","RADIO")).build();
        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).isEmpty();
    }

    @Test
    @DisplayName("When car brand is null")
    void test2(){
        var car = Car.builder().id(2).model("GOLF").price(BigDecimal.valueOf(99999)).build();

        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).containsEntry("Brand", "Is null, empty or not matching regex");
    }
    @Test
    @DisplayName("When car model is null")
    void test3(){
        var car = Car.builder().id(2).price(BigDecimal.ONE).build();

        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).containsEntry("Model", "Is null, empty or not matching regex");
    }

    @Test
    @DisplayName("When car price below zero")
    void test4() {
        var car = Car.builder().id(2).price(BigDecimal.valueOf(-10)).build();

        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).containsEntry("Price", "Has to be positive number");
    }

    @Test
    @DisplayName("When car year in limit")
    void test5() {
        var car = Car.builder().id(2).brand("RENAULT").model("CLIO").price(BigDecimal.valueOf(9000)).year(2020).color(Color.RED).mileage(10000).build();
        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).isEmpty();
    }
    @Test
    @DisplayName("When car year below limit")
    void test6() {
        var car = Car.builder().id(2).brand("FIAT").model("PUNTO").price(BigDecimal.valueOf(5000)).year(1800).color(Color.YELLOW).mileage(99999).build();

        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).containsEntry("Year", "Not correct");
    }

    @Test
    @DisplayName("When mileage is lower than 0")
    void test7() {
        var car = Car.builder().id(1).brand("FIAT").model("MULTIPLA").price(BigDecimal.valueOf(5000)).year(1800).color(Color.YELLOW).mileage(-9).build();

        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).containsEntry("Mileage", "Has to be positive");
    }

    @Test
    @DisplayName("When components not matching regex")
    void test8(){
        var car = Car.builder().id(2).brand("NISSAN").model("PATROL").price(BigDecimal.valueOf(25000)).year(1999).color(Color.WHITE).mileage(55000).components(List.of("ABS", "esp", "radio")).build();

        var errors = carValidator.validate(car);
        Assertions.assertThat(errors).containsEntry("Component", "Not all matching regex");
    }
}
