package com.patternproject.controller;

import com.patternproject.model.CalculatorModel;
import com.patternproject.view.CalculatorView;

import com.patternproject.test.util.TestUtil;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Koliadin Nikita
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorControllerImplTest {

    @Mock
    private CalculatorModel calculatorModelMock;
    @Mock
    private CalculatorView calculatorViewMock;

    private CalculatorControllerImpl calculatorController;
    private CalculatorControllerImpl calculatorControllerEmpty;

    @Before
    public void setUp() {
        calculatorController = new CalculatorControllerImpl();
        calculatorControllerEmpty = new CalculatorControllerImpl();

        calculatorController.setCalculatorModel(calculatorModelMock);
        calculatorController.setCalculatorView(calculatorViewMock);

        System.setIn(TestUtil.CONSOLE_INPUT_STREAM);
        System.setOut(TestUtil.CONSOLE_PRINT_STREAM);

        when((calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)")).thenReturn(1d);
        when((calculatorModelMock).calculate("15+3")).thenReturn(18d);
        when((calculatorModelMock).calculate("sqrt(225)")).thenReturn(15d);
        when((calculatorModelMock).calculate("tan(0)")).thenReturn(0d);
    }

    @Test
    public void shouldCreateObject() {
        assertThat(calculatorController).isNotNull();
        assertThat(calculatorControllerEmpty).isNotNull();
        assertThat(calculatorModelMock).isNotNull();
        assertThat(new CalculatorControllerImpl(calculatorModelMock, calculatorViewMock)).isNotNull();
    }

    @Test
    public void shouldImplements() {
        assertThat(calculatorController).isInstanceOf(CalculatorController.class);
        assertThat(calculatorControllerEmpty).isInstanceOf(CalculatorController.class);
        assertThat(calculatorModelMock).isInstanceOf(CalculatorModel.class);
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
        calculatorController.calculateToConsoleFrom(new StringReader(";  ; ;;;  \b  ; \n;\n\n \t\t; \t; \t \t\r \t;  "));

        verifyZeroInteractions(calculatorModelMock);
    }

    @Test
    public void shouldOutputToConsole() {
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorController.calculateToConsoleFrom(new StringReader("sin(1)*sin(1)+cos(1)*cos(1);15+3;sqrt(225);tan(0)"));

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator()
                + "18.0" + System.lineSeparator()
                + "15.0" + System.lineSeparator()
                + "0.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldPassSeparatedExpressions() {
        System.out.println("====[TEST] shouldPassSeparatedExpressions [TEST]====");

        calculatorController.calculateToConsoleFrom(new StringReader("sin(1)*sin(1)+cos(1)*cos(1);15+3;sqrt(225);tan(0)"));

        verify(calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verify(calculatorModelMock).calculate("15+3");
        verify(calculatorModelMock).calculate("sqrt(225)");
        verify(calculatorModelMock).calculate("tan(0)");
        verifyNoMoreInteractions(calculatorModelMock);
    }

    @Test
    public void shouldIdentifySpace() {
        System.out.println("====[TEST] shouldIdentifySpace [TEST]====");

        when((calculatorModelMock).calculate("  tan(0)")).thenReturn(0d);

        calculatorController.calculateToConsoleFrom(new StringReader("sqrt(225);  tan(0)"));

        verify(calculatorModelMock).calculate("sqrt(225)");
        verify(calculatorModelMock).calculate("  tan(0)");
        verifyNoMoreInteractions(calculatorModelMock);
    }

    @Test
    public void shouldWorkMethodStartDefaultCalculate() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorController.startDefaultCalculate();

        verify(calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verifyNoMoreInteractions(calculatorModelMock);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldWorkMethodCalculateToConsoleFrom() throws UnsupportedEncodingException {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorController.calculateToConsoleFrom(new InputStreamReader(System.in, "UTF-8"));

        verify(calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verifyNoMoreInteractions(calculatorModelMock);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldWorkMethodCalculateFromConsoleTo() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorController.calculateFromConsoleTo(System.out::println);

        verify(calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verifyNoMoreInteractions(calculatorModelMock);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldWorkMethodCalculateFromTo() throws UnsupportedEncodingException {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        calculatorController.calculateFromTo(
                new InputStreamReader(System.in, "UTF-8"),
                System.out::println
        );

        verify(calculatorModelMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verifyNoMoreInteractions(calculatorModelMock);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
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
        assertThatNullPointerException().isThrownBy(
                () -> calculatorControllerEmpty.calculateToConsoleFrom(new StringReader("15 + 5"))
        ).withMessage(null);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenReaderOfCalculatorIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorController.calculateToConsoleFrom(null)
        ).withMessage("reader is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenDoubleConsumerOfCalculatorIsNull() {
        assertThatNullPointerException().isThrownBy(
                () -> calculatorController.calculateFromConsoleTo(null)
        ).withMessage("resultConsumer is marked @NonNull but is null");
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemOutIsNull() {
        System.setOut(null);

        assertThatNullPointerException().isThrownBy(
                () -> calculatorController.calculateToConsoleFrom(new StringReader("15 + 5"))
        ).withMessage(null);
    }

    @Test
    public void shouldCloseInputStreamWhenThrowIllegalArgumentException() throws IOException {
        val input = "end";

        val byteArrayInputStream = spy(new ByteArrayInputStream(input.getBytes()));

        System.setIn(byteArrayInputStream);

        when((calculatorModelMock).calculate("end")).thenThrow(IllegalArgumentException.class);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> calculatorController.startDefaultCalculate()
        );

        verify(calculatorModelMock).calculate("end");
        verify(byteArrayInputStream, times(1)).close();
        verifyNoMoreInteractions(calculatorModelMock);
    }
}
