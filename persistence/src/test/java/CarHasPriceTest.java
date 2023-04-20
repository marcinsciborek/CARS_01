import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class CarHasPriceTest {

    @Test
    @DisplayName("When proper price is expected")
    void test1(){
        var car = Car.builder().price(BigDecimal.valueOf(50000)).build();
        Assertions.assertThat(car.hasPriceBetween(BigDecimal.valueOf(0), BigDecimal.valueOf(100000))).isTrue();
    }

    @Test
    @DisplayName("When price is lower than expected")
    void test2(){
        var car = Car.builder().price(BigDecimal.valueOf(5000)).build();
        Assertions.assertThat(car.hasPriceBetween(BigDecimal.valueOf(6000), BigDecimal.valueOf(1000000))).isFalse();
    }

    @Test
    @DisplayName("When price is higher than expected")
    void test3(){
        var car = Car.builder().price(BigDecimal.valueOf(5000)).build();
        Assertions.assertThat(car.hasPriceBetween(BigDecimal.valueOf(1), BigDecimal.valueOf(3000))).isFalse();
    }
}
