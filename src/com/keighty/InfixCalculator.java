package com.keighty;

import java.util.Stack;

public class InfixCalculator {
    private Stack operatorStack = new Stack();
    private Stack operandStack = new Stack();

    public int calculate(String expression) throws InvalidOperatorException {
        char[] charArr = expression.toCharArray();
        for (char item :
                charArr) {
            if (Character.isDigit(item)) {
                operandStack.push(Character.getNumericValue(item));
            } else {
                operatorStack.push(item);
            }
        }

        while ( !operatorStack.isEmpty() ) {
            char operator = (char) operatorStack.pop();
            int value1 = (int) operandStack.pop();
            int value2 = (int) operandStack.pop();
            operandStack.push(handleOperator(operator, value2, value1));

        }

        return (int) operandStack.pop();
    }

    private int handleOperator(char operator, int operand1, int operand2) throws InvalidOperatorException {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new InvalidOperatorException("invalid operator: " + operator);
        }
    }
}
