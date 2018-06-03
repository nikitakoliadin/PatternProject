package com.patternproject.model;

import com.patternproject.exceptions.InvalidInputException;
import com.patternproject.util.MathFunctionsUtil;

import lombok.val;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Koliadin Nikita
 *
 * This class implements the calculator using JavaScript engine - Nashorn.
 */
public class CalculatorNashornModel implements CalculatorModel {

    /**
     * This method implements the calculator engine due to the Javascript
     * engine - Nashorn.
     * @param expression is input parameter that can have math functions
     *                   and  any kind of math operations.
     * @return result of calculation.
     */
    @Override
    public double calculate(String expression) {
        val scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            defineMathFunctions(scriptEngine);
            return ((Number) scriptEngine.eval(expression)).doubleValue();
        } catch (ScriptException | ClassCastException e) {
            throw new InvalidInputException("Failed to evaluate expression", e);
        }
    }

    private static void defineMathFunctions(ScriptEngine scriptEngine) throws ScriptException {
        for (val function : MathFunctionsUtil.MATH_FUNCTIONS) {
            scriptEngine.eval("function " + function + "(x) { return Java.type('java.lang.Math')." + function + "(x); }");
        }
    }
}
