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
        printGreeting();

        calculatorController.startDefaultCalculate();
    }

    private void printGreeting() {
        log.info("Preparing to print greeting to the console");

        System.out.println("-> Hello!" + System.lineSeparator()
                + "-> I'm your calculator today!" + System.lineSeparator()
                + "-> To exit print: exit()"
        );
        log.info("Preparing to print greeting to the console was done successful! " +
                "The greeting is displayed on the console");
    }
}
