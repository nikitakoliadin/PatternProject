package com.qthegamep.patternproject.view;

import com.qthegamep.patternproject.controller.CalculatorController;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.function.DoubleConsumer;

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

    /**
     * This method implements outputs the result.
     *
     * @param resultConsumer result output parameter.
     * @param result         result of the calculation.
     */
    @Override
    public void outputResult(DoubleConsumer resultConsumer, double result) {
        log.info("Preparing to output result of the calculation");

        resultConsumer.accept(result);
        log.info("Preparing to output result of the calculation was done successful! Waiting next input");
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
