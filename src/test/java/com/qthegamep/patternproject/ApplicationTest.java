package com.qthegamep.patternproject;

import com.qthegamep.patternproject.test.rule.TimingRules;

import lombok.val;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import static com.qthegamep.patternproject.Application.main;
import static org.assertj.core.api.Assertions.*;

public class ApplicationTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;
    @Rule
    public ExternalResource inputOutputSetup = TimingRules.INPUT_OUTPUT_SETUP;

    @Test
    public void shouldWorkApplication() {
        val input = "sin(1)*sin(1)+cos(1)*cos(1)";

        val byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        val byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(byteArrayInputStream);
        System.setOut(new PrintStream(byteArrayOutputStream));

        main(null);

        val actual = byteArrayOutputStream.toString();
        val expected = "-> Hello!" + System.lineSeparator()
                + "-> I'm your calculator today!" + System.lineSeparator()
                + "-> To exit print: exit()" + System.lineSeparator()
                + "1.0" + System.lineSeparator();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldThrowInvocationTargetExceptionWhenCreateObjectWithReflection() {
        assertThatExceptionOfType(InvocationTargetException.class).isThrownBy(() -> {
            val constructor = Application.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        }).withMessage(null).withCauseInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemInIsNull() {
        System.setIn(null);

        assertThatNullPointerException().isThrownBy(
                () -> main(null)
        ).withMessage(null);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenSystemOutIsNull() {
        System.setOut(null);

        assertThatNullPointerException().isThrownBy(
                () -> main(null)
        ).withMessage(null);
    }
}
