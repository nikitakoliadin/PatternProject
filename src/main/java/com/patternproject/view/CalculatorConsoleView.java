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
public class CalculatorConsoleView implements CalculatorView {

    @NonNull
    @Getter
    @Setter
    private CalculatorController calculatorController;

    /**
     * This method implements the start of the default calculator.
     * Also the method must log data.
     */
    @Override
    public void run() {
        log.info("Class: {}, Method: {} - was started successful",
                this.getClass(),
                new Object() {}.getClass().getEnclosingMethod().getName()
        );

        System.out.println("Hello! I'm your calculator this day!");
        System.out.println("To exit print -> exit()");

        log.info("The greeting is displayed on the console successfully");

        calculatorController.startDefaultCalculate();
    }
}
