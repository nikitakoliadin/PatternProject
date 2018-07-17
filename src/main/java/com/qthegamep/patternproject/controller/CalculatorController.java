package com.qthegamep.patternproject.controller;

import com.qthegamep.patternproject.model.CalculatorModel;
import com.qthegamep.patternproject.view.CalculatorView;

import java.io.Reader;
import java.util.function.DoubleConsumer;

/**
 * This class is a calculator controller.
 * It should have a standard console implementation of the calculator,
 * as well as a customizable implementation of the calculator.
 */
public interface CalculatorController {

    /**
     * @return parameter of the calculator model.
     */
    CalculatorModel getCalculatorModel();

    /**
     * @param calculatorModel parameter of the calculator model.
     */
    void setCalculatorModel(CalculatorModel calculatorModel);

    /**
     * @return parameter of the calculator view.
     */
    CalculatorView getCalculatorView();

    /**
     * @param calculatorView parameter of the calculator view.
     */
    void setCalculatorView(CalculatorView calculatorView);

    /**
     * This method should be a standard implementation of the calculator.
     * Incoming data comes from the console.
     * Result output to the console.
     */
    void startDefaultCalculate();

    /**
     * This method should take Reader parameter.
     * Incoming data comes from the reader.
     * Result output to the console.
     *
     * @param reader information reading parameters.
     */
    void calculateToConsoleFrom(Reader reader);

    /**
     * This method should take DoubleConsumer parameter.
     * Incoming data comes from the console.
     * Result output to the resultConsumer.
     *
     * @param resultConsumer result output parameter.
     */
    void calculateFromConsoleTo(DoubleConsumer resultConsumer);

    /**
     * This method should take Reader and DoubleConsumer parameters.
     * Incoming data comes from the console.
     * Result output to the resultConsumer.
     *
     * @param reader         information reading parameters.
     * @param resultConsumer result output parameter.
     */
    void calculateFromTo(Reader reader, DoubleConsumer resultConsumer);
}
