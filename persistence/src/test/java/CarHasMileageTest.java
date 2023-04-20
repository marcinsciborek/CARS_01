import com.app.model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarHasMileageTest {
    @Test
    @DisplayName("When mileage is below limit")
    void test1() {
        var car = Car.builder().mileage(50000).build();
        Assertions.assertThat(car.hasMileageLowerThan(60000)).isTrue();
    }

    @Test
    @DisplayName("When mileage higher than limit")
    void test2() {
        var car = Car.builder().mileage(101).build();
        Assertions.assertThat(car.hasMileageLowerThan(100)).isFalse();
    }

}
