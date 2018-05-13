package com.patternproject.model;

import lombok.val;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Koliadin Nikita
 *
 * This class implements the calculatorEngine using JavaScript engine - Nashorn
 */
public class CalculatorNashorn implements Calculator {

    /**
     * This method implements the calculatorEngine engine due to the Java Script
     * engine - Nashorn
     * @param expression is input parameter that can have math functions
     *                   and  any kind of math operations
     * @return result of calculation
     */
    @Override
    public double calculate(String expression) {
        val scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            defineMathFunctions(scriptEngine);
            return ((Number) scriptEngine.eval(expression)).doubleValue();
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Failed to evaluate expression", e);
        }
    }

    private static void defineMathFunctions(ScriptEngine scriptEngine) throws ScriptException {
        for (val function : new String[] {"sin", "cos", "sqrt", "tan"}) {
            scriptEngine.eval("function " + function + "(x) { return Java.type('java.lang.Math')." + function + "(x); }");
        }
    }
}
