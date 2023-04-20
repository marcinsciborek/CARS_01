import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarHasYearTest {
    @Test
    @DisplayName("When production year is proper")
    void test1(){
        var car = Car.builder().year(2000).build();
        Assertions.assertThat(car.hasYearBetween(1995, 2005)).isTrue();
    }

    @Test
    @DisplayName("When production year is lower than expected")
    void test2() {
        var car = Car.builder().year(2000).build();
        Assertions.assertThat(car.hasYearBetween(2001, 2005)).isFalse();
    }

    @Test
    @DisplayName("When production year is higher than expected")
    void test3() {
        var car = Car.builder().year(2010).build();
        Assertions.assertThat(car.hasYearBetween(2000, 2005)).isFalse();
    }
}
