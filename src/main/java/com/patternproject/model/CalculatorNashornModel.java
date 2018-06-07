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
public class CalculatorNashornModel implements CalculatorModel {

    /**
     * This method implements the calculator engine due to the Javascript
     * engine - Nashorn.
     *
     * @param expression is input parameter that can have math functions
     *                   and  any kind of math operations.
     * @return result of calculation.
     */
    @Override
    public double calculate(String expression) {
        log.info("Method [{}] was started successful with parameter [{}]",
                new Object() {}.getClass().getEnclosingMethod().getName(),
                expression
        );

        val scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

        log.info("Script engine class [{}] was loaded successful", scriptEngine.getClass());

        try {
            defineMathFunctions(scriptEngine);

            log.info("Defined math functions successful");

            val result = ((Number) scriptEngine.eval(expression)).doubleValue();

            log.info("Result of the expression is [{}]", result);

            return result;
        } catch (ScriptException | ClassCastException e) {
            log.error("Failed to evaluate expression");

            throw new InvalidInputException("Failed to evaluate expression", e);
        }
    }

    private void defineMathFunctions(ScriptEngine scriptEngine) throws ScriptException {
        log.info("Method [{}] was started successful",
                new Object() {}.getClass().getEnclosingMethod().getName()
        );

        for (val function : MathFunctionsUtil.MATH_FUNCTIONS) {
            scriptEngine.eval("function " + function +
                    "(x) { return Java.type('java.lang.Math')." + function + "(x); }");

            log.info("Function [{}] was defined successful", function);
        }
    }
}
