package com.patternproject;

import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Koliadin Nikita
 */
public class ApplicationTest {

    private static final InputStream CONSOLE_INPUT_STREAM = System.in;
    private static final PrintStream CONSOLE_PRINT_STREAM = System.out;

    private Application application;

    @Before
    public void setUp() {
        application = new Application();
    }

    @After
    public void tearDown() {
        System.setIn(ApplicationTest.CONSOLE_INPUT_STREAM);
        System.setOut(ApplicationTest.CONSOLE_PRINT_STREAM);
    }

    @Test
    public void shouldCreateObject() {
        assertThat(application).isNotNull().isInstanceOf(Application.class);
    }

    @Test
    public void shouldCalculateExpression() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        Application.main(null);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCalculateALotOfExpression() {
        val firstInput = "sin(1)*sin(1)+cos(1)*cos(1)";
        val secondInput = "(sin(1)*sin(1)+cos(1)*cos(1))+(tan(1)*(1/tan(1)))";
        val thirdInput = "tan(0)+sqrt(225)";

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(firstInput.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        Application.main(null);

        byteArrayInputStream = new ByteArrayInputStream(secondInput.getBytes());
        System.setIn(byteArrayInputStream);

        Application.main(null);

        byteArrayInputStream = new ByteArrayInputStream(thirdInput.getBytes());
        System.setIn(byteArrayInputStream);

        Application.main(null);

        val actual = byteArrayOutputStream.toString();
        val expected = "1.0" + System.lineSeparator()
                + "2.0" + System.lineSeparator()
                + "15.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldThrowNullPointerException() {
        System.setIn(null);
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> Application.main(null));
    }
}
