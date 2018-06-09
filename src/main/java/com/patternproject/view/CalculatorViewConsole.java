package com.patternproject.view;

import com.patternproject.controller.CalculatorController;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Koliadin Nikita
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorViewConsole implements CalculatorView {

    @NonNull
    @Getter
    @Setter
    private CalculatorController calculatorController;

    /**
     * This method implements the start of the default console calculator.
     */
    @Override
    public void startConsoleCalculator() {
        log.info("Preparing to out greeting to the console");

        printGreeting();
        log.info("The greeting is displayed on the console successful");

        calculatorController.startDefaultCalculate();
    }

    private void printGreeting() {
        System.out.println("-> Hello!" + System.lineSeparator()
                + "-> I'm your calculator today!" + System.lineSeparator()
                + "-> To exit print: exit()"
        );
    }
}
