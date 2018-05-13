package com.patternproject.controller;

import com.patternproject.model.Calculator;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.DoubleConsumer;
import java.util.stream.Stream;

/**
 * @author Koliadin Nikita
 *
 * This class is decorator of Calculator that has input and output to console
 * which can be setted
 */
public class ConsoleCalculator implements CalculatorController {

    @Getter @Setter private Calculator calculatorEngine;

    public ConsoleCalculator() {
    }

    public ConsoleCalculator(Calculator calculatorEngine) {
        this.calculatorEngine = calculatorEngine;
    }

    /**
     * This method implements the beginning of work with the calculator.
     * It does not have any input parameters. The output is carried out
     * to the console, the reading is done from the console also
     */
    @Override
    public void startDefaultCalculate() {
        try (Reader reader = new InputStreamReader(System.in)) {
            calculateToConsoleInOut(reader);
        } catch (IOException ignore) {
           /* Never happen */
        }
    }

    /**
     * This method takes Reader parameter and after calculation of the expression
     * out the result to console in the new line. If expression divided by ';'
     * then each result out to the new line. Empty lines are not calculated
     * @param reader information reading parameters
     */
    public void calculateToConsoleInOut(Reader reader) {
        consoleCalculator(reader, System.out::println);
    }

    private void consoleCalculator(Reader reader, DoubleConsumer resultConsumer) {
        new BufferedReader(reader).lines()
                .flatMap((s) -> Stream.of(s.split(";")))
                .filter((s) -> !s.trim().isEmpty())
                .mapToDouble(calculatorEngine::calculate)
                .forEach(resultConsumer);
    }
}
