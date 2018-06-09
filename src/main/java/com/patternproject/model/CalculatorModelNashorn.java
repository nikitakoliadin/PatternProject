package com.patternproject.model;

import com.patternproject.exceptions.InvalidInputException;
import com.patternproject.util.MathFunctionsUtil;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Koliadin Nikita
 *
 * This class implements the calculator using JavaScript engine - Nashorn.
 */
@Slf4j
public class CalculatorModelNashorn implements CalculatorModel {

    /**
     * This method implements the calculator engine due to the Javascript engine - Nashorn.
     *
     * @param expression is input parameter that can have math functions and  any kind of math operations.
     * @return result of calculation.
     */
    @Override
    public double calculate(String expression) {
        log.info("Preparing to calculate expression: [{}]", expression);

        val scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        log.info("Script engine: [{}] was loaded successful", scriptEngine.getClass());

        try {
            defineMathFunctions(scriptEngine);
            log.info("Preparing to define math functions was successful");

            val result = ((Number) scriptEngine.eval(expression)).doubleValue();
            log.info("Preparing to calculate expression: [{}] was successful! Result of the expression: [{}]",
                    expression,
                    result
            );

            return result;
        } catch (ScriptException | ClassCastException e) {
            log.error("Failed to evaluate expression, message: [{}]",
                    e.getMessage(),
                    e
            );
            throw new InvalidInputException("Failed to evaluate expression", e);
        }
    }

    private void defineMathFunctions(ScriptEngine scriptEngine) throws ScriptException {
        log.info("Preparing to define math functions");

        for (val function : MathFunctionsUtil.MATH_FUNCTIONS) {
            scriptEngine.eval("function " + function +
                    "(x) { return Java.type('java.lang.Math')." + function + "(x); }");
            log.info("Function: [{}] was defined successful", function);
        }
    }
}
