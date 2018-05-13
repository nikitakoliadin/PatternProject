package com.patternproject;

import com.patternproject.model.CalculatorNashorn;
import com.patternproject.controller.ConsoleCalculator;

import lombok.val;

/**
 * @author Koliadin Nikita
 */
public class Main {
    public static void main(String[] args) {
        val calculatorNashorn = new CalculatorNashorn(); // Model
        val consoleCalculator = new ConsoleCalculator(); // Controller

        consoleCalculator.setCalculatorEngine(calculatorNashorn);

        consoleCalculator.startDefaultCalculate();
    }
}
