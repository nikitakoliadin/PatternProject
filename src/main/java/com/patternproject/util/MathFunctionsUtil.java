package com.patternproject.util;

import com.patternproject.exceptions.UtilClassException;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;

import java.util.List;

/**
 * @author Koliadin Nikita
 */
@Slf4j
public final class MathFunctionsUtil {

    /**
     * This constant contains a immutable list of functions available for use in the calculator.
     */
    public static final List<String> MATH_FUNCTIONS = List.of(
            "abs", "acos", "asin", "atan", "cbrt", "ceil", "cos", "cosh", "exp", "expm1", "floor",
            "getExponent", "log", "log10", "log1p", "nextDown", "nextUp", "rint", "round", "signum",
            "sin", "sinh", "sqrt", "tan", "tanh", "toDegrees", "toRadians", "ulp"
    );

    @Contract(" -> fail")
    private MathFunctionsUtil() {
        log.error("Can not create object of util class");
        throw new UtilClassException("Can not create object of util class");
    }
}
