package extensions;

import com.app.model.car.CarValidator;
import org.junit.jupiter.api.extension.*;


public class Extension_ParameterResolverCarValidator implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarValidator.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return new CarValidator("[A-Z\\d ]+", 1885, 120000);

    }
}
