package com.patternproject.view;

import com.patternproject.controller.CalculatorController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Koliadin Nikita
 */
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorConsoleView implements CalculatorView {

    @Getter @Setter private CalculatorController calculatorController;

    /**
     * This method implements the start of the default calculator
     */
    @Override
    public void run() {
        System.out.println("Hello! I'm you calculator this day!");
        System.out.println("To exit print -> exit()");
        calculatorController.startDefaultCalculate();
    }
}
