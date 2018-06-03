package com.patternproject.controller;

import com.patternproject.model.CalculatorModel;
import com.patternproject.view.CalculatorView;

import lombok.*;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.function.DoubleConsumer;

/**
 * @author Koliadin Nikita
 */
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorConsoleController implements CalculatorController {

    @NonNull @Getter @Setter private CalculatorModel calculatorModel;
    @NonNull @Getter @Setter private CalculatorView calculatorView;

    /**
     * This method implements the beginning of work with the calculator.
     * It does not have any input parameters. The output is carried out
     * to the console, the reading is done from the console also.
     * If expression divided by ';' then each result out to the new line.
     * Empty lines are not calculated.
     */
    @Override
    public void startDefaultCalculate() {
        try (val reader = new InputStreamReader(System.in, "UTF-8")) {
            calculateToConsoleInOut(reader);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    /**
     * This method takes Reader parameter and after calculation of the expression
     * out the result to console in the new line. If expression divided by ';'
     * then each result out to the new line. Empty lines are not calculated
     * @param reader information reading parameters.
     */
    public void calculateToConsoleInOut(Reader reader) {
        consoleCalculator(reader, System.out::println);
    }

    private void consoleCalculator(Reader reader, DoubleConsumer resultConsumer) {
        new BufferedReader(reader).lines()
                .flatMap((s) -> Stream.of(s.split(";")))
                .filter((s) -> !s.trim().isEmpty())
                .mapToDouble(calculatorModel::calculate)
                .forEach(resultConsumer);
    }
}
