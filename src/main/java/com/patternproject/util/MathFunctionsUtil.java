package com.patternproject.util;

import com.patternproject.exceptions.UtilClassException;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koliadin Nikita
 */
public final class MathFunctionsUtil {

    @Contract(" -> fail")
    private MathFunctionsUtil() {
        throw new UtilClassException("Can not create object of util class");
    }

    /**
     * This constant contains a list of functions available for use in the calculator
     */
    public static final List<String> MATH_FUNCTIONS = new ArrayList<>(){{
        add("abs");
        add("acos");
        add("asin");
        add("atan");
        add("cbrt");
        add("ceil");
        add("cos");
        add("cosh");
        add("exp");
        add("expm1");
        add("floor");
        add("getExponent");
        add("log");
        add("log10");
        add("log1p");
        add("nextDown");
        add("nextUp");
        add("rint");
        add("round");
        add("signum");
        add("sin");
        add("sinh");
        add("sqrt");
        add("tan");
        add("tanh");
        add("toDegrees");
        add("toRadians");
        add("ulp");
    }};
}
