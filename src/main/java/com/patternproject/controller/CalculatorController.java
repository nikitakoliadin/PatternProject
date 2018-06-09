package com.patternproject.controller;

import java.io.Reader;
import java.util.function.DoubleConsumer;

/**
 * @author Koliadin Nikita
 */
public interface CalculatorController {

    /**
     * This method should be a standard implementation of the calculator that parses the incoming data and
     * outputs the result. Input and output comas from console.
     */
    void startDefaultCalculate();

    /**
     * This method should take Reader parameter and after calculation of the expression out the result to console
     * in the new line.
     *
     * @param reader information reading parameters.
     */
    void calculateToConsoleFrom(Reader reader);

    /**
     * This method should take DoubleConsumer parameter and after calculation of the expression out the result
     * to DoubleConsumer. Empty lines are not calculated.
     *
     * @param resultConsumer result output parameter.
     */
    void calculateFromConsoleTo(DoubleConsumer resultConsumer);

    /**
     * This method read expression to calculate from Reader and then output result to DoubleConsumer.
     *
     * @param reader         information reading parameters.
     * @param resultConsumer result output parameter.
     */
    void calculateFromTo(Reader reader, DoubleConsumer resultConsumer);
}
