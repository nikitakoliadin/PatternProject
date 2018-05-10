package com;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {

    private Main main;

    @Before
    public void setUp() {
        main = new Main();
    }

    @Test
    public void shouldBigger() {
        assertTrue(Main.isBigger(6,3));
    }

    @Test
    public void shouldNotBigger() {
        assertFalse(Main.isBigger(5, 5));
    }

    @Test
    public void shouldDivide() {
        assertEquals(5, main.divide(10, 2));
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowArithmeticException() {
        main.divide(10, 0);
    }

    @Test
    public void shouldEqualsOutputToConsole() {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Main.main(null);

        System.setOut(consoleStream);

        String actual = byteArrayOutputStream.toString();
        String expected = "true" + System.lineSeparator() +
                "true" + System.lineSeparator() +
                "5" + System.lineSeparator() +
                "1" + System.lineSeparator();

        assertEquals(expected, actual);
    }
}
