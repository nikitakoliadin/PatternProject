package com.patternproject.view;

import com.patternproject.controller.CalculatorController;

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

import static com.patternproject.test.rule.TimingRules.INPUT_OUTPUT_SETUP;
import static com.patternproject.test.rule.TimingRules.STOPWATCH;
import static com.patternproject.test.rule.TimingRules.SUMMARY;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Koliadin Nikita
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorViewConsoleTest {

    @ClassRule
    public static ExternalResource summary = SUMMARY;

    @Rule
    public Stopwatch stopwatch = STOPWATCH;
    @Rule
    public ExternalResource inputOutputSetup = INPUT_OUTPUT_SETUP;

    @Mock
    private CalculatorController calculatorControllerMock;

    private CalculatorViewConsole calculatorView;
    private CalculatorViewConsole calculatorViewEmpty;

    @Before
    public void setUp() {
        calculatorView = new CalculatorViewConsole();
        calculatorViewEmpty = new CalculatorViewConsole();

        calculatorView.setCalculatorController(calculatorControllerMock);
    }

    @Test
    public void shouldCreateObject() {
        assertThat(calculatorView).isNotNull();
        assertThat(calculatorViewEmpty).isNotNull();
        assertThat(calculatorControllerMock).isNotNull();
        assertThat(new CalculatorViewConsole(calculatorControllerMock)).isNotNull();
    }

    @Test
    public void shouldImplements() {
        assertThat(calculatorView).isInstanceOf(CalculatorView.class);
        assertThat(calculatorViewEmpty).isInstanceOf(CalculatorView.class);
        assertThat(calculatorControllerMock).isInstanceOf(CalculatorController.class);
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
        calculatorView.startConsoleCalculator();

        verify(calculatorControllerMock).startDefaultCalculate();
        verifyNoMoreInteractions(calculatorControllerMock);
    }

    @Test
    public void shouldPrintWelcomeWhenCalculatorRun() {
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorView.startConsoleCalculator();

        val actual = byteArrayOutputStream.toString();
        val expected = "-> Hello!" + System.lineSeparator()
                + "-> I'm your calculator today!" + System.lineSeparator()
                + "-> To exit print: exit()" + System.lineSeparator();

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
        );
    }
}
