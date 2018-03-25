package com;

import org.junit.Before;
import org.junit.Test;

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
        Main.main(null);
    }
}