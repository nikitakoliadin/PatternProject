package com.patternproject.view;

import com.patternproject.controller.CalculatorController;
import com.patternproject.controller.CalculatorControllerImpl;

import com.patternproject.test.rule.TimingRules;

import lombok.val;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Koliadin Nikita
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewConsoleTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;
    @Rule
    public ExternalResource inputOutputSetup = TimingRules.INPUT_OUTPUT_SETUP;

    @Mock
    private CalculatorController calculatorControllerMock;

    private CalculatorView calculatorViewMock;
    private CalculatorView calculatorViewReal;
    private CalculatorView calculatorViewEmpty;

    @Before
    public void setUp() {
        calculatorViewMock = new CalculatorViewConsole();
        calculatorViewReal = new CalculatorViewConsole(new CalculatorControllerImpl());
        calculatorViewEmpty = new CalculatorViewConsole();

        calculatorViewMock.setCalculatorController(calculatorControllerMock);
    }

    @Test
    public void shouldCreateObject() {
        assertThat(calculatorViewMock).isNotNull();
        assertThat(calculatorViewReal).isNotNull();
        assertThat(calculatorViewEmpty).isNotNull();
    }

    @Test
    public void shouldImplements() {
        assertThat(calculatorViewMock).isInstanceOf(CalculatorView.class);
        assertThat(calculatorViewReal).isInstanceOf(CalculatorView.class);
        assertThat(calculatorViewEmpty).isInstanceOf(CalculatorView.class);
    }

    @Test
    public void shouldGetAndSetController() {
        calculatorViewEmpty.setCalculatorController(calculatorControllerMock);

        assertThat(calculatorViewEmpty.getCalculatorController()).isNotNull()
                .isEqualTo(calculatorControllerMock)
                .isInstanceOf(CalculatorController.class);
    }

    @Test
    public void shouldRunDefaultRunner() {
        calculatorViewMock.startConsoleCalculator();

        verify(calculatorControllerMock).startDefaultCalculate();
        verifyNoMoreInteractions(calculatorControllerMock);
    }

    @Test
    public void shouldPrintWelcomeWhenCalculatorRun() {
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorViewMock.startConsoleCalculator();

        val actual = byteArrayOutputStream.toString();
        val expected = "-> Hello!" + System.lineSeparator()
                + "-> I'm your calculator today!" + System.lineSeparator()
                + "-> To exit print: exit()" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldWorkMethodOutputResult() {
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorViewReal.outputResult(System.out::println, 15d);

        val actual = byteArrayOutputStream.toString();
        val expected = "15.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenControllerParameterOfArgsConstructorIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> new CalculatorViewConsole(null)
        ).withMessage("calculatorController is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSetCalculatorControllerToNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorViewEmpty.setCalculatorController(null)
        ).withMessage("calculatorController is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenControllerIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorViewEmpty.startConsoleCalculator()
        ).withMessage(null);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemOutIsNull() {
        System.setOut(null);

        assertThatNullPointerException().isThrownBy(
                () -> calculatorViewReal.startConsoleCalculator()
        ).withMessage(null);
    }
}
