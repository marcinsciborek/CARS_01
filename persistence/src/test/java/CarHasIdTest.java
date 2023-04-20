import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarHasIdTest {

    @Test
    @DisplayName("When id is ok")
    void test1() {
        var car = Car.builder().id(1).build();
        Assertions.assertThat(car.hasId(1)).isTrue();
    }

    @Test
    @DisplayName("When id is null")
    void test2() {
        var car = Car.builder().id(5).build();
        Assertions.assertThat(car.hasId(null)).isFalse();
    }

}
