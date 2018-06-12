package com.patternproject.view;

import java.util.function.DoubleConsumer;

/**
 * @author Koliadin Nikita
 */
public interface CalculatorView {

    /**
     * This method should be a default implementation of the preparation and start of the console calculator.
     */
    void startConsoleCalculator();

    /**
     * This method should outputs the result.
     *
     * @param resultConsumer result output parameter.
     * @param result         result of the calculation.
     */
    void outputResult(DoubleConsumer resultConsumer, double result);
}
