import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarHasModelTest {
    @Test
    @DisplayName("When model is correct")
    void test1() {
        var car = Car.builder().brand("MERCEDES").build();
        Assertions.assertThat(car.hasBrand("MERCEDES")).isTrue();
    }

    @Test
    @DisplayName("When model is not correct")
    void test2() {
        var car = Car.builder().brand("MERCEDES").build();
        Assertions.assertThat(car.hasBrand("Audi")).isFalse();
    }

    @Test
    @DisplayName("When model is empty")
    void test3() {
        var car = Car.builder().brand(" ").build();
        Assertions.assertThat(car.hasBrand(" ")).isTrue();
    }
}
