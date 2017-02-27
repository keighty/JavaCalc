package com.keighty;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

/**
 * Created by kleonard on 2/27/17.
 */
public class InfixCalculatorTest extends InfixCalculator {

    private InfixCalculator icalc;
    private String testExpression1 = "1 + 2 * 3";

    private enum Expression {
        ADDITION("1+2+3", 6),
        SUBTRACTION("9-4", 5),
        MULTIPLICATION("5*4*2", 40),
        DIVISION("9/3", 3);

        final String expression;
        final int result;
        Expression(String expression, int result) {
            this.expression = expression;
            this.result = result;
        }
    }

    @org.junit.Before
    public void setUp() throws Exception {
        icalc = new InfixCalculator();
    }

    @Test
    public void test_solves_an_expression() throws InvalidOperatorException {
        for (Expression exp : Expression.values()) {
            assertEquals("for expression " + exp.expression, exp.result, icalc.calculate(exp.expression));
        }
    }
}