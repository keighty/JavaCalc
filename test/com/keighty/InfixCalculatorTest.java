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
        DIVISION("9/3", 3),
        ADDITION_WITH_SPACES("5 + 7", 12),
        MULTI_DIGITS("12 + 3 - 5", 10),
        MULTI_DIGIT_MULTIPLICATION("12*6*2", 144),
        MIXED_MULTIPLY_AND_ADD("12 * 6 + 2", 74),
        MIXED_MULTIPLY_AND_ADD_INVERSE("2 + 12 * 6", 74);

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

    @Test
    public void test_solves_one_expression() throws InvalidOperatorException {
        Expression exp = Expression.MIXED_MULTIPLY_AND_ADD;
        assertEquals("for expression " + exp.expression, exp.result, icalc.calculate(exp.expression));
    }

    @Test(expected=InvalidOperatorException.class)
    public void test_handles_invalid_input() throws InvalidOperatorException {
        String invalidInput = "a+b+c";
        icalc.calculate(invalidInput);

    }
}