import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarHasBrandTest {

    @Test
    @DisplayName("When brand is correct")
    void test1() {
        var car = Car.builder().brand("BMW").build();
        Assertions.assertThat(car.hasBrand("BMW")).isTrue();
    }

    @Test
    @DisplayName("When brand is not correct")
    void test2() {
        var car = Car.builder().brand("BMW").build();
        Assertions.assertThat(car.hasBrand("Audi")).isFalse();
    }

    @Test
    @DisplayName("When brand is empty")
    void test3() {
        var car = Car.builder().brand(" ").build();
        Assertions.assertThat(car.hasBrand(" ")).isTrue();
    }
}