package com.patternproject.test.util;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author Koliadin Nikita
 */
@UtilityClass
public class TestUtil {

    /**
     * This constant contain InputStream to console.
     */
    public final InputStream CONSOLE_INPUT_STREAM = System.in;

    /**
     * This constant contain PrintStream to console.
     */
    public final PrintStream CONSOLE_PRINT_STREAM = System.out;

    /**
     * This method set input and output to the console. Use it before and after each test where the input
     * or output is substituted.
     */
    public void setInputOutputToConsole() {
        System.setIn(TestUtil.CONSOLE_INPUT_STREAM);
        System.setOut(TestUtil.CONSOLE_PRINT_STREAM);
    }
}
