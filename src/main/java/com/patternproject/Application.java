package com.patternproject;

import com.patternproject.model.CalculatorNashornModel;
import com.patternproject.view.CalculatorConsoleView;
import com.patternproject.controller.CalculatorConsoleController;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * @author Koliadin Nikita
 */
@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("Preparing application to start...");

        val calculatorModel = new CalculatorNashornModel();
        val calculatorController = new CalculatorConsoleController();
        val calculatorView = new CalculatorConsoleView();

        calculatorController.setCalculatorModel(calculatorModel);
        calculatorController.setCalculatorView(calculatorView);
        calculatorView.setCalculatorController(calculatorController);

        log.info("Preparing was done successful!");

        calculatorView.run();
    }
}
