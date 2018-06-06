package com.patternproject.view;

import com.patternproject.controller.CalculatorController;

import lombok.*;

/**
 * @author Koliadin Nikita
 */
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
        System.out.println("Hello! I'm you calculator this day!");
        System.out.println("To exit print -> exit()");
        calculatorController.startDefaultCalculate();
    }
}
