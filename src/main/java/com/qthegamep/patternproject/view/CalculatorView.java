package com.qthegamep.patternproject.view;

import com.qthegamep.patternproject.controller.CalculatorController;

import java.util.function.DoubleConsumer;

/**
 * This interface is view of the calculator.
 * The implementation of this interface should output the results of the calculations.
 */
public interface CalculatorView {

    /**
     * @return parameter of the calculator controller.
     */
    CalculatorController getCalculatorController();

    /**
     * @param calculatorController parameter of the calculator controller.
     */
    void setCalculatorController(CalculatorController calculatorController);

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
