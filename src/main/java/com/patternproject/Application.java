package com.patternproject;

import com.patternproject.model.CalculatorNashornModel;
import com.patternproject.view.CalculatorConsoleView;
import com.patternproject.controller.CalculatorConsoleController;

import lombok.val;

/**
 * @author Koliadin Nikita
 */
public class Application {
    public static void main(String[] args) {
        val calculatorModel = new CalculatorNashornModel();
        val calculatorController = new CalculatorConsoleController();
        val calculatorView =  new CalculatorConsoleView();

        calculatorController.setCalculatorModel(calculatorModel);
        calculatorController.setCalculatorView(calculatorView);
        calculatorView.setCalculatorController(calculatorController);

        calculatorView.run();
    }
}
