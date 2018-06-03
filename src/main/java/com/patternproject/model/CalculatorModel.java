package com.patternproject.model;

/**
 * @author Koliadin Nikita
 */
@FunctionalInterface
public interface CalculatorModel {

    /**
     * This method implement the calculation of the expression
     * that comes as a string.
     * @param expression is input parameter that can have math functions
     *                   and  any kind of math operations.
     * @return double value after calculation of the expression.
     */
    double calculate(String expression);
}
