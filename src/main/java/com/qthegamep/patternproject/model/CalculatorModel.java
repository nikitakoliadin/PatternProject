package com.qthegamep.patternproject.model;

/**
 * This class is a calculator model.
 * It must be able to make calculations.
 * It must accept already processed parameters for calculations.
 */
@FunctionalInterface
public interface CalculatorModel {

    /**
     * This method implement the calculation of the expression that comes as a string.
     *
     * @param expression is input parameter that can have math functions and  any kind of math operations.
     * @return double value after calculation of the expression.
     */
    double calculate(String expression);
}
