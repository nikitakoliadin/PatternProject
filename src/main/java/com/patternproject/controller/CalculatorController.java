package com.patternproject.controller;

/**
 * @author Koliadin Nikita
 *
 * This functional interface describes the control of the calculatorEngine
 */
@FunctionalInterface
public interface CalculatorController {

    /**
     * This method should be a default implementation of the start
     * of the calculator
     */
    void startDefaultCalculate();
}
