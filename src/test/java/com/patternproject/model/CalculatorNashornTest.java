package com.patternproject.model;

import org.junit.Before;
import org.junit.Test;

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
        assertThat(calculator).isNotNull().isInstanceOf(Calculator.class);
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
    public void shouldCalculateBigExpression() {
        String expression = "";
        for (int i = 0; i < 10; i++) {
            expression += "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))" +
                    "+(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))";
        }

        assertThat(calculator.calculate(expression)).isEqualTo(200);
    }

    @Test
    public void shouldNotCalculateNegativeSqrt() {
        assertThat(calculator.calculate("sqrt(-4)")).isNaN();
    }

    @Test
    public void shouldNotDivideByZero() {
        assertThat(calculator.calculate("15/0*1+5")).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void shouldNotDivideZeroByZero() {
        assertThat(calculator.calculate("0/0+0/0")).isNaN();
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenExpressionIsIncorrect() {
        assertThatIllegalArgumentException().isThrownBy(() -> calculator.calculate("end"));
    }

    @Test
    public void shouldThrowArgumentExceptionWhenCalculateDoubleMinus() {
        assertThatIllegalArgumentException().isThrownBy(() -> calculator.calculate("1--1"));
    }
}
