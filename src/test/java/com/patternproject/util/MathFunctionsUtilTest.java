package com.patternproject.util;

import lombok.val;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Koliadin Nikita
 */
public class MathFunctionsUtilTest {

    @Test
    public void shouldBeCorrectListOfMathFunctions() {
        val expected = List.of(
                "abs", "acos", "asin", "atan", "cbrt", "ceil", "cos", "cosh", "exp", "expm1", "floor",
                "getExponent", "log", "log10", "log1p", "nextDown", "nextUp", "rint", "round", "signum",
                "sin", "sinh", "sqrt", "tan", "tanh", "toDegrees", "toRadians", "ulp"
        );

        assertThat(MathFunctionsUtil.MATH_FUNCTIONS).isNotNull().hasSize(28).isEqualTo(expected);
    }

    @Test
    public void shouldThrowUnsupportedOperationExceptionWhenDoPutOperationsWithImmutableList() {
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
                () -> MathFunctionsUtil.MATH_FUNCTIONS.add("a")
        );
    }

    @Test
    public void shouldThrowInvocationTargetExceptionWhenCreateObjectWithReflection() {
        assertThatExceptionOfType(InvocationTargetException.class).isThrownBy(() -> {
            val constructor = MathFunctionsUtil.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }).withCauseInstanceOf(UnsupportedOperationException.class);
    }
}
