package com.patternproject.util;

import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author Koliadin Nikita
 */
@UtilityClass
public class MathFunctionsUtil {

    /**
     * This constant contains a immutable list of functions available for use in the calculator.
     */
    public final List<String> MATH_FUNCTIONS = List.of(
            "abs", "acos", "asin", "atan", "cbrt", "ceil", "cos", "cosh", "exp", "expm1", "floor",
            "getExponent", "log", "log10", "log1p", "nextDown", "nextUp", "rint", "round", "signum",
            "sin", "sinh", "sqrt", "tan", "tanh", "toDegrees", "toRadians", "ulp"
    );
}
