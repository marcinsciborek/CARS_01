
import com.app.impl.CarServiceImpl;
import com.app.repository.CarRepository;
import com.app.repository.exception.CarRepositoryImplException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static com.app.Cars.CAR_1_AUDI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CarServiceFindAllByBrandTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    @DisplayName("when brand is null")
    void test1() {
        assertThatThrownBy(() -> carService.findAllByBrand(null, "[A-Z]+"))
                .isInstanceOf(CarRepositoryImplException.class)
                .hasMessage("Brand is not correct");
    }

    @Test
    void test2() {
        assertThatThrownBy(() -> carService.findAllByBrand("B", null))
                .isInstanceOf(CarRepositoryImplException.class)
                .hasMessage("Brand is not correct");
    }

    @Test
    void test3() {
        assertThatThrownBy(() -> carService.findAllByBrand(null, null))
                .isInstanceOf(CarRepositoryImplException.class)
                .hasMessage("Brand is not correct");
    }

    @Test
    void test4() {
        assertThatThrownBy(() -> carService.findAllByBrand("B", "[a-z]+"))
                .isInstanceOf(CarRepositoryImplException.class)
                .hasMessage("Brand is not correct");
    }

    @Test
    void test5() {
        when(carRepository.findAll())
                .thenReturn(List.of(CAR_1_AUDI));

        assertThat(carService.findAllByBrand("BMW", "[A-Z]+"))
                .isEmpty();
    }

    @Test
    void test6() {
        when(carRepository.findAll())
                .thenReturn(List.of(CAR_1_AUDI));

        assertThat(carService.findAllByBrand("AUDI", "[A-Z]+"))
                .hasSize(1);
    }

}
