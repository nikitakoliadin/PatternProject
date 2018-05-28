package com.patternproject.model;

import com.patternproject.exceptions.InvalidInputException;

import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptException;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Koliadin Nikita
 */
public class CalculatorNashornTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new CalculatorNashorn();
    }

    @Test
    public void shouldCreateObject() {
        assertThat(calculator).isNotNull();
    }

    @Test
    public void shouldImplements() {
        assertThat(calculator).isInstanceOf(Calculator.class);
    }

    @Test
    public void shouldReturnZero() {
        assertThat(calculator.calculate("0")).isZero();
    }

    @Test
    public void shouldDivideWithOffset() {
        assertThat(calculator.calculate("16/6")).isCloseTo(2.66666, offset(1e-5));
    }

    @Test
    public void shouldCalculateDifferentOperations() {
        assertThat(calculator.calculate("77-65+94/95*2.17")).isCloseTo(14.14715, offset(1e-5));
    }

    @Test
    public void shouldReturnNegativeNumbers() {
        assertThat(calculator.calculate("-3e3")).isEqualTo(-3000);
    }

    @Test
    public void shouldCalculateFunctions() {
        assertThat(calculator.calculate("(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))")).isEqualTo(2);
    }

    @Test
    public void shouldInvertSign() {
        assertThat(calculator.calculate("-1*-1*-1*-(-1)*-(1)*-1")).isEqualTo(-1);
    }

    @Test
    public void shouldDivideZeroByNumber() {
        assertThat(calculator.calculate("0/15+15")).isEqualTo(15);
    }

    @Test
    public void shouldReturnPositiveInfinityWhenPositiveNumberDivideByZero() {
        assertThat(calculator.calculate("15/0*1+5")).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void shouldReturnNegativeInfinityWhenNegativeNumberDivideByZero() {
        assertThat(calculator.calculate("-15/0*1+5")).isEqualTo(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void shouldCalculateBigExpression() {
        String expression = "";
        for (int i = 0; i < 100; i++) {
            expression += "+(sin(1)*sin(1)+cos(1)*cos(1))+(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))";
        }

        assertThat(calculator.calculate(expression)).isEqualTo(300);
    }

    @Test
    public void shouldNotCalculateNegativeSqrt() {
        assertThat(calculator.calculate("sqrt(-4)")).isNaN();
    }

    @Test
    public void shouldNotDivideZeroByZero() {
        assertThat(calculator.calculate("0/0+0/0")).isNaN();
    }

    @Test
    public void shouldThrowInvalidInputExceptionWhenExpressionIsIncorrect() {
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(
                () -> calculator.calculate("exit")
        ).withMessage("Failed to evaluate expression").withCauseInstanceOf(ClassCastException.class);
    }

    @Test
    public void shouldThrowInvalidInputExceptionWhenCalculateDoubleMinus() {
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(
                () -> calculator.calculate("1--1")
        ).withMessage("Failed to evaluate expression").withCauseInstanceOf(ScriptException.class);
    }
}
