package com;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {

    private Main main;

    @Before
    public void init() {
        main = new Main();
    }

    @Test
    public void isBigger() {
        assertTrue(Main.isBigger(6,3));
        assertTrue(Main.isBigger(5,4));
        assertFalse(Main.isBigger(5, 5));
    }

    @Test
    public void divide() {
        assertEquals(main.divide(10, 2), 5);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowExceptionArithmeticException() {
        main.divide(10, 0);
    }

    @Test
    public void main() {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Main.main(null);

        System.setOut(consoleStream);

        String result = byteArrayOutputStream.toString();
        String mustBe = "true" + System.lineSeparator() +
                "true" + System.lineSeparator() +
                "5" + System.lineSeparator() +
                "1" + System.lineSeparator();

        assertEquals(result, mustBe);
    }
}
