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
    public void shouldCalculateEachFunctionCorrectly() {
        assertThat(calculator.calculate("abs(-15.5)")).isEqualTo(15.5);
        assertThat(calculator.calculate("acos(1)")).isEqualTo(0);
        assertThat(calculator.calculate("asin(0)")).isEqualTo(0);
        assertThat(calculator.calculate("atan(0)")).isEqualTo(0);
        assertThat(calculator.calculate("cbrt(-8)")).isEqualTo(-2);
        assertThat(calculator.calculate("ceil(-15.9)")).isEqualTo(-15);
        assertThat(calculator.calculate("cos(1)")).isCloseTo(0.540302305, offset(1e-9));
        assertThat(calculator.calculate("cosh(1)")).isCloseTo(1.543080634, offset(1e-9));
        assertThat(calculator.calculate("exp(1)")).isCloseTo(2.718281828, offset(1e-9));
        assertThat(calculator.calculate("expm1(1)")).isCloseTo(1.718281828, offset(1e-9));
        assertThat(calculator.calculate("floor(1.9)")).isEqualTo(1);
        assertThat(calculator.calculate("getExponent(22)")).isEqualTo(4);
        assertThat(calculator.calculate("log(25)")).isCloseTo(3.218875824, offset(1e-9));
        assertThat(calculator.calculate("log10(100)")).isEqualTo(2);
        assertThat(calculator.calculate("log1p(25)")).isCloseTo(3.258096538, offset(1e-9));
        assertThat(calculator.calculate("nextDown(1)")).isCloseTo(0.999999940, offset(1e-9));
        assertThat(calculator.calculate("nextUp(1)")).isCloseTo(1.000000119, offset(1e-9));
        assertThat(calculator.calculate("rint(15.49)")).isEqualTo(15);
        assertThat(calculator.calculate("rint(15.5)")).isEqualTo(16);
        assertThat(calculator.calculate("round(-16.5)")).isEqualTo(-16);
        assertThat(calculator.calculate("round(16.5)")).isEqualTo(17);
        assertThat(calculator.calculate("signum(15)")).isEqualTo(1);
        assertThat(calculator.calculate("sin(1)")).isCloseTo(0.841470984, offset(1e-9));
        assertThat(calculator.calculate("sinh(1)")).isCloseTo(1.175201193, offset(1e-9));
        assertThat(calculator.calculate("sqrt(4)")).isEqualTo(2);
        assertThat(calculator.calculate("tan(1)")).isCloseTo(1.557407724, offset(1e-9));
        assertThat(calculator.calculate("tanh(1)")).isCloseTo(0.761594155, offset(1e-9));
        assertThat(calculator.calculate("toDegrees(1)")).isCloseTo(57.295779513, offset(1e-9));
        assertThat(calculator.calculate("toRadians(100)")).isCloseTo(1.745329251, offset(1e-9));
        assertThat(calculator.calculate("ulp(100)")).isCloseTo(0.000007629, offset(1e-9));
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
