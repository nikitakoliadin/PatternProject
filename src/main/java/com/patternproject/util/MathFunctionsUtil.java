package com.patternproject.util;

import com.patternproject.exceptions.UtilClassException;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koliadin Nikita
 */
public final class MathFunctionsUtil {

    /**
     * This constant contains a list of functions available for use in the calculator
     */
    public static final List<String> MATH_FUNCTIONS = new ArrayList<>();

    static {
        MATH_FUNCTIONS.add("abs");
        MATH_FUNCTIONS.add("acos");
        MATH_FUNCTIONS.add("asin");
        MATH_FUNCTIONS.add("atan");
        MATH_FUNCTIONS.add("cbrt");
        MATH_FUNCTIONS.add("ceil");
        MATH_FUNCTIONS.add("cos");
        MATH_FUNCTIONS.add("cosh");
        MATH_FUNCTIONS.add("exp");
        MATH_FUNCTIONS.add("expm1");
        MATH_FUNCTIONS.add("floor");
        MATH_FUNCTIONS.add("getExponent");
        MATH_FUNCTIONS.add("log");
        MATH_FUNCTIONS.add("log10");
        MATH_FUNCTIONS.add("log1p");
        MATH_FUNCTIONS.add("nextDown");
        MATH_FUNCTIONS.add("nextUp");
        MATH_FUNCTIONS.add("rint");
        MATH_FUNCTIONS.add("round");
        MATH_FUNCTIONS.add("signum");
        MATH_FUNCTIONS.add("sin");
        MATH_FUNCTIONS.add("sinh");
        MATH_FUNCTIONS.add("sqrt");
        MATH_FUNCTIONS.add("tan");
        MATH_FUNCTIONS.add("tanh");
        MATH_FUNCTIONS.add("toDegrees");
        MATH_FUNCTIONS.add("toRadians");
        MATH_FUNCTIONS.add("ulp");
    }

    @Contract(" -> fail")
    private MathFunctionsUtil() {
        throw new UtilClassException("Can not create object of util class");
    }
}
