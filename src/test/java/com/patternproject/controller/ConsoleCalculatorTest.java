package com.patternproject.controller;

import com.patternproject.model.Calculator;

import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Koliadin Nikita
 */
public class ConsoleCalculatorTest {

    private Calculator calculatorMock;
    private ConsoleCalculator calculator;
    private ConsoleCalculator calculatorEmptyEngine;

    @Before
    public void setUp()  {
        calculatorMock = mock(Calculator.class);
        calculator = new ConsoleCalculator(calculatorMock);
        calculatorEmptyEngine = new ConsoleCalculator();
    }

    @Test
    public void shouldCreateObject() {
        assertThat(calculator).isNotNull();
        assertThat(calculatorEmptyEngine).isNotNull();
        assertThat(calculatorMock).isNotNull();
    }

    @Test
    public void shouldImplements() {
        assertThat(calculator).isInstanceOf(CalculatorController.class);
        assertThat(calculatorEmptyEngine).isInstanceOf(CalculatorController.class);
        assertThat(calculatorMock).isInstanceOf(Calculator.class);
    }

    @Test
    public void shouldSetAndGetCalculator() {
        calculatorEmptyEngine.setCalculatorEngine(calculatorMock);

        assertThat(calculatorEmptyEngine.getCalculatorEngine()).isNotNull().isEqualTo(calculatorMock);
    }

    @Test
    public void shouldSkipEmptyExpression() {
        calculator.calculateToConsoleInOut(new StringReader(";  ; ;;;  \b  ; \n;\n\n \t\t; \t; \t \t\r \t;  "));

        verifyZeroInteractions(calculatorMock);
    }

    @Test
    public void shouldOutputToConsole() {
        val consolePrintStream = System.out;
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));

        when((calculatorMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)")).thenReturn(1d);
        when((calculatorMock).calculate("15+3")).thenReturn(18d);
        when((calculatorMock).calculate("sqrt(225)")).thenReturn(15d);
        when((calculatorMock).calculate("tan(0)")).thenReturn(0d);

        calculator.calculateToConsoleInOut(new StringReader("sin(1)*sin(1)+cos(1)*cos(1);15+3;sqrt(225);tan(0)"));

        System.setOut(consolePrintStream);

        val actual = byteArrayOutputStream.toString();
        val expected =  "1.0" + System.lineSeparator()
                + "18.0" + System.lineSeparator()
                + "15.0" + System.lineSeparator()
                + "0.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldPassSeparatedExpressions() {
        System.out.println("====[TEST] shouldPassSeparatedExpressions [TEST]====");

        when((calculatorMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)")).thenReturn(1d);
        when((calculatorMock).calculate("15+3")).thenReturn(18d);
        when((calculatorMock).calculate("sqrt(225)")).thenReturn(15d);
        when((calculatorMock).calculate("tan(0)")).thenReturn(0d);

        calculator.calculateToConsoleInOut(new StringReader("sin(1)*sin(1)+cos(1)*cos(1);15+3;sqrt(225);tan(0)"));

        verify(calculatorMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verify(calculatorMock).calculate("15+3");
        verify(calculatorMock).calculate("sqrt(225)");
        verify(calculatorMock).calculate("tan(0)");
        verifyNoMoreInteractions(calculatorMock);
    }

    @Test
    public void shouldWorkDefaultStartCalculate() {
        val consoleInputStream = System.in;
        val consolePrintStream = System.out;

        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        when((calculatorMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)")).thenReturn(1d);

        calculator.startDefaultCalculate();

        verify(calculatorMock).calculate("sin(1)*sin(1)+cos(1)*cos(1)");
        verifyNoMoreInteractions(calculatorMock);

        System.setIn(consoleInputStream);
        System.setOut(consolePrintStream);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldIdentifySpace() {
        System.out.println("====[TEST] shouldIdentifySpace [TEST]====");

        when((calculatorMock).calculate("sqrt(225)")).thenReturn(15d);
        when((calculatorMock).calculate("  tan(0)")).thenReturn(0d);

        calculator.calculateToConsoleInOut(new StringReader("sqrt(225);  tan(0)"));

        verify(calculatorMock).calculate("sqrt(225)");
        verify(calculatorMock).calculate("  tan(0)");
        verifyNoMoreInteractions(calculatorMock);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenCalculatorEngineIsNull() {
        assertThatNullPointerException().isThrownBy(() -> calculatorEmptyEngine.calculateToConsoleInOut(new StringReader("15 + 5")));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemOutIsNull() {
        val consolePrintStream = System.out;
        System.setOut(null);

        assertThatNullPointerException().isThrownBy(() -> calculator.calculateToConsoleInOut(new StringReader("15 + 5")));

        System.setOut(consolePrintStream);
    }

    @Test
    public void shouldCloseInputStreamWhenThrowIllegalArgumentException() throws IOException {
        val consoleInputStream = System.in;

        val input = "end";
        val byteArrayInputStream = spy(new ByteArrayInputStream(input.getBytes()));

        System.setIn(byteArrayInputStream);

        when((calculatorMock).calculate("end")).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> calculator.startDefaultCalculate()).isInstanceOf(IllegalArgumentException.class);

        verify(calculatorMock).calculate("end");
        verify(byteArrayInputStream, times(1)).close();
        verifyNoMoreInteractions(calculatorMock);

        System.setIn(consoleInputStream);
    }
}
