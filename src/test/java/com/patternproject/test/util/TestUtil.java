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
}
