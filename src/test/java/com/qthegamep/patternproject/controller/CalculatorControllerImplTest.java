package com.qthegamep.patternproject.controller;

import com.qthegamep.patternproject.exception.InvalidInputException;
import com.qthegamep.patternproject.model.CalculatorModel;
import com.qthegamep.patternproject.model.CalculatorModelNashorn;
import com.qthegamep.patternproject.view.CalculatorView;
import com.qthegamep.patternproject.view.CalculatorViewConsole;

import com.qthegamep.patternproject.test.rule.TimingRules;

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

import java.io.*;
import java.util.function.DoubleConsumer;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Koliadin Nikita
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorControllerImplTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;
    @Rule
    public ExternalResource inputOutputSetup = TimingRules.INPUT_OUTPUT_SETUP;

    @Mock
    private CalculatorModel calculatorModelMock;
    @Mock
    private CalculatorView calculatorViewMock;
    @Mock
    private DoubleConsumer doubleConsumer;

    private CalculatorController calculatorControllerMock;
    private CalculatorController calculatorControllerReal;
    private CalculatorController calculatorControllerEmpty;

    @Before
    public void setUp() {
        calculatorControllerMock = new CalculatorControllerImpl();
        calculatorControllerReal = new CalculatorControllerImpl(new CalculatorModelNashorn(), new CalculatorViewConsole());
        calculatorControllerEmpty = new CalculatorControllerImpl();

        calculatorControllerMock.setCalculatorModel(calculatorModelMock);
        calculatorControllerMock.setCalculatorView(calculatorViewMock);

        when((calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)")).thenReturn(1d);
        when((calculatorModelMock).calculate("15+3")).thenReturn(18d);
        when((calculatorModelMock).calculate("sqrt(225)")).thenReturn(15d);
        when((calculatorModelMock).calculate("tan(0)")).thenReturn(0d);

        doNothing().when(calculatorViewMock).outputResult(doubleConsumer, 1d);
        doNothing().when(calculatorViewMock).outputResult(doubleConsumer, 18d);
        doNothing().when(calculatorViewMock).outputResult(doubleConsumer, 15d);
        doNothing().when(calculatorViewMock).outputResult(doubleConsumer, 0d);
    }

    @Test
    public void shouldCreateObject() {
        assertThat(calculatorControllerMock).isNotNull();
        assertThat(calculatorControllerReal).isNotNull();
        assertThat(calculatorControllerEmpty).isNotNull();
    }

    @Test
    public void shouldImplements() {
        assertThat(calculatorControllerMock).isInstanceOf(CalculatorController.class);
        assertThat(calculatorControllerReal).isInstanceOf(CalculatorController.class);
        assertThat(calculatorControllerEmpty).isInstanceOf(CalculatorController.class);
    }

    @Test
    public void shouldGetAndSetModel() {
        calculatorControllerEmpty.setCalculatorModel(calculatorModelMock);

        assertThat(calculatorControllerEmpty.getCalculatorModel()).isNotNull().isEqualTo(calculatorModelMock).isInstanceOf(CalculatorModel.class);
    }

    @Test
    public void shouldGetAndSetView() {
        calculatorControllerEmpty.setCalculatorView(calculatorViewMock);

        assertThat(calculatorControllerEmpty.getCalculatorView()).isNotNull().isEqualTo(calculatorViewMock).isInstanceOf(CalculatorView.class);
    }

    @Test
    public void shouldSkipEmptyExpression() {
        calculatorControllerMock.calculateToConsoleFrom(new StringReader(";  ; ;;;  \b  ; \n;\n\n \t\t; \t; \t \t\r \t;  "));

        verifyZeroInteractions(calculatorModelMock);
        verifyZeroInteractions(calculatorViewMock);
    }

    @Test
    public void shouldPassSeparatedExpressions() {
        calculatorControllerMock.calculateToConsoleFrom(new StringReader("sin(1)*sin(1)+cos(1)*cos(1);15+3;sqrt(225);tan(0)"));

        verify(calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verify(calculatorModelMock).calculate("15+3");
        verify(calculatorModelMock).calculate("sqrt(225)");
        verify(calculatorModelMock).calculate("tan(0)");

        verifyNoMoreInteractions(calculatorModelMock);
    }

    @Test
    public void shouldCallCorrectTimes() {
        calculatorControllerMock.calculateFromTo(new StringReader("sin(1)*sin(1)+cos(1)*cos(1);15+3;sqrt(225);tan(0)"), doubleConsumer);

        verify(calculatorModelMock, times(1)).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verify(calculatorModelMock, times(1)).calculate("15+3");
        verify(calculatorModelMock, times(1)).calculate("sqrt(225)");
        verify(calculatorModelMock, times(1)).calculate("tan(0)");

        verifyNoMoreInteractions(calculatorModelMock);

        verify(calculatorViewMock, times(1)).outputResult(doubleConsumer, 1d);
        verify(calculatorViewMock, times(1)).outputResult(doubleConsumer, 18d);
        verify(calculatorViewMock, times(1)).outputResult(doubleConsumer, 15d);
        verify(calculatorViewMock, times(1)).outputResult(doubleConsumer, 0d);

        verifyNoMoreInteractions(calculatorViewMock);
    }

    @Test
    public void shouldIdentifySpaces() {
        when((calculatorModelMock).calculate("  tan(0)")).thenReturn(0d);

        calculatorControllerMock.calculateFromTo(new StringReader("sqrt(225);  tan(0)"), doubleConsumer);

        verify(calculatorModelMock, times(1)).calculate("sqrt(225)");
        verify(calculatorModelMock, times(1)).calculate("  tan(0)");

        verifyNoMoreInteractions(calculatorModelMock);

        verify(calculatorViewMock, times(1)).outputResult(doubleConsumer, 15d);
        verify(calculatorViewMock, times(1)).outputResult(doubleConsumer, 0d);

        verifyNoMoreInteractions(calculatorViewMock);
    }

    @Test
    public void shouldOutputToConsole() {
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorControllerReal.calculateToConsoleFrom(new StringReader("sin(1)*sin(1)+cos(1)*cos(1);15+3;sqrt(225);tan(0)"));

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator()
                + "18.0" + System.lineSeparator()
                + "15.0" + System.lineSeparator()
                + "0.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCallMethodStartDefaultCalculate() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(byteArrayInputStream);

        calculatorControllerMock.startDefaultCalculate();

        verify(calculatorModelMock, times(1)).calculate(input);

        verifyNoMoreInteractions(calculatorModelMock);
    }

    @Test
    public void shouldWorkMethodStartDefaultCalculate() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorControllerReal.startDefaultCalculate();

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCallMethodCalculateToConsoleFrom() throws UnsupportedEncodingException {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(byteArrayInputStream);

        calculatorControllerMock.calculateToConsoleFrom(new InputStreamReader(System.in, "UTF-8"));

        verify(calculatorModelMock, times(1)).calculate(input);

        verifyNoMoreInteractions(calculatorModelMock);
    }

    @Test
    public void shouldWorkMethodCalculateToConsoleFrom() throws UnsupportedEncodingException {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorControllerReal.calculateToConsoleFrom(new InputStreamReader(System.in, "UTF-8"));

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCallMethodCalculateFromConsoleTo() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(byteArrayInputStream);

        calculatorControllerMock.calculateFromConsoleTo(System.out::println);

        verify(calculatorModelMock, times(1)).calculate(input);

        verifyNoMoreInteractions(calculatorModelMock);
    }

    @Test
    public void shouldWorkMethodCalculateFromConsoleTo() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorControllerReal.calculateFromConsoleTo(System.out::println);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCallMethodCalculateFromTo() throws UnsupportedEncodingException {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(byteArrayInputStream);

        calculatorControllerMock.calculateFromTo(
                new InputStreamReader(System.in, "UTF-8"),
                System.out::println
        );

        verify(calculatorModelMock, times(1)).calculate(input);

        verifyNoMoreInteractions(calculatorModelMock);
    }

    @Test
    public void shouldWorkMethodCalculateFromTo() throws UnsupportedEncodingException {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorControllerReal.calculateFromTo(
                new InputStreamReader(System.in, "UTF-8"),
                System.out::println
        );

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCloseInputStreamWhenWorkWasDone() throws IOException {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = spy(new ByteArrayInputStream(input.getBytes()));

        System.setIn(byteArrayInputStream);

        calculatorControllerReal.startDefaultCalculate();

        verify(byteArrayInputStream, times(1)).close();
    }

    @Test
    public void shouldCloseInputStreamWhenInvalidInputException() throws IOException {
        val input = "end";

        val byteArrayInputStream = spy(new ByteArrayInputStream(input.getBytes()));

        System.setIn(byteArrayInputStream);

        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(
                () -> calculatorControllerReal.startDefaultCalculate()
        ).withMessage("Failed to evaluate expression");

        verify(byteArrayInputStream, times(1)).close();
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenModelParameterOfArgsConstructorIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> new CalculatorControllerImpl(null, calculatorViewMock)
        ).withMessage("calculatorModel is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenViewParameterOfArgsConstructorIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> new CalculatorControllerImpl(calculatorModelMock, null)
        ).withMessage("calculatorView is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenBothParametersOfArgsConstructorAreNull() {
        assertThatNullPointerException().isThrownBy(
                () -> new CalculatorControllerImpl(null, null)
        ).withMessage("calculatorModel is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSetCalculatorModelToNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerEmpty.setCalculatorModel(null)
        ).withMessage("calculatorModel is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSetCalculatorViewToNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerEmpty.setCalculatorView(null)
        ).withMessage("calculatorView is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenCalculatorModelIsNull() {
        calculatorControllerEmpty.setCalculatorView(new CalculatorViewConsole());

        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerEmpty.calculateToConsoleFrom(new StringReader("15 + 5"))
        ).withMessage(null);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenCalculatorViewIsNull() {
        calculatorControllerEmpty.setCalculatorModel(new CalculatorModelNashorn());

        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerEmpty.calculateToConsoleFrom(new StringReader("15 + 5"))
        ).withMessage(null);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenReaderOfCalculatorIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerReal.calculateToConsoleFrom(null)
        ).withMessage("reader is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenDoubleConsumerOfCalculatorIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerReal.calculateFromConsoleTo(null)
        ).withMessage("resultConsumer is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemOutIsNull() {
        System.setOut(null);

        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerReal.calculateToConsoleFrom(new StringReader("15 + 5"))
        ).withMessage(null);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemInIsNull() {
        System.setIn(null);

        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerReal.calculateToConsoleFrom(new StringReader("15 + 5"))
        ).withMessage(null);
    }
}
