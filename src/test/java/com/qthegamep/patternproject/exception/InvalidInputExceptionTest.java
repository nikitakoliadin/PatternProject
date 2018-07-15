package com.qthegamep.patternproject.exception;

import com.qthegamep.patternproject.test.rule.TimingRules;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;

import javax.script.ScriptException;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class InvalidInputExceptionTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;

    @Test
    public void shouldThrowInvalidInputExceptionCorrect() {
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> {
            throw new InvalidInputException("Failed to evaluate expression", new ScriptException(""));
        }).withMessage("Failed to evaluate expression").withCauseInstanceOf(ScriptException.class);
    }
}
