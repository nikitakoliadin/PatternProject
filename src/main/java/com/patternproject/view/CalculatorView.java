package com.patternproject.view;

/**
 * @author Koliadin Nikita
 */
@FunctionalInterface
public interface CalculatorView {

    /**
     * This method should be a default implementation of the preparation
     * and start of the console calculator.
     * Any logging or processing of output must be encapsulated in the implementation.
     */
    void run();
}
