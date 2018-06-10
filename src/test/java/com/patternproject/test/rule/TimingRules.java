package com.patternproject.test.rule;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Koliadin Nikita
 */
@UtilityClass
public class TimingRules {

    /**
     * The constant is used for calculating of the time spent by a test. Use it in @Rule JUnit annotation.
     */
    public final Stopwatch STOPWATCH = new Stopwatch() {

        @Override
        protected void finished(long nanos, @NotNull Description description) {
            val result = String.format("%-112s %7d",
                    description.getDisplayName(),
                    TimeUnit.NANOSECONDS.toMillis(nanos)
            );

            className = description.getClassName();
            results.append(result).append(System.lineSeparator());
            log.info(System.lineSeparator() + result);
        }
    };

    /**
     * The constant is used for output results of the class tests. Use it in @ClassRule JUnit annotation.
     */
    public final ExternalResource SUMMARY = new ExternalResource() {

        @Override
        protected void before() {
            results.setLength(0);
        }

        @Override
        protected void after() {
            val infoLine = String.format("Test of %-99s %12s",
                    className,
                    "Duration, ms"
            );

            log.info(System.lineSeparator()
                    + "------------------------------------------------------------------------------------------------------------------------"
                    + System.lineSeparator()
                    + infoLine
                    + System.lineSeparator()
                    + "------------------------------------------------------------------------------------------------------------------------"
                    + System.lineSeparator()
                    + results
                    + "------------------------------------------------------------------------------------------------------------------------"
            );
        }
    };

    private final Logger log = LoggerFactory.getLogger("testResultLogger");

    private final StringBuilder results = new StringBuilder();

    private String className;
}
