package com.patternproject;

import com.patternproject.model.CalculatorNashorn;
import com.patternproject.controller.ConsoleCalculator;

import lombok.val;

/**
 * @author Koliadin Nikita
 */
public class Application {
    public static void main(String[] args) {
        val calculatorNashorn = new CalculatorNashorn();
        val consoleCalculator = new ConsoleCalculator();

        consoleCalculator.setCalculatorEngine(calculatorNashorn);

        consoleCalculator.startDefaultCalculate();
    }
}
