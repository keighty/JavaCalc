package com.keighty;

import java.util.Stack;

public class InfixCalculator {
    private Stack operatorStack = new Stack<Character>();
    private Stack operandStack = new Stack<Integer>();
    boolean popStack = false;

    public int calculate(String expression) {
        populateStacks(expression);
        evaluateStacks();
        return (int) operandStack.pop();
    }

    private void populateStacks(String expression) {
        boolean seenNumeric = false;
        char[] charArr = expression.toCharArray();
        for (char item :
                charArr) {
            if (Character.isDigit(item)) {
                if (seenNumeric) {
                    int prevInt = (int) operandStack.pop();
                    String newInt = String.valueOf(prevInt) + String.valueOf(item);
                    operandStack.push(Integer.valueOf(newInt));
                } else {
                    operandStack.push(Character.getNumericValue(item));
                    if (popStack) {
                        evaluateExpression();
                        popStack = false;
                    }
                    seenNumeric = true;
                }
            } else if (! Character.isSpaceChar(item)) {
                seenNumeric = false;
                handleOperatorCharacter(item);
                operatorStack.push(item);
            }
        }
    }

    private void handleOperatorCharacter(char operator) {
        switch (operator) {
            case '*':case '/':
                popStack = true;
                break;
            default:
                popStack = false;
        }
    }

    private void evaluateStacks() {
        while ( !operatorStack.isEmpty() ) {
            evaluateExpression();
        }
    }

    private void evaluateExpression() {
        char operator = (char) operatorStack.pop();
        int value1 = (int) operandStack.pop();
        int value2 = (int) operandStack.pop();
        try {
            operandStack.push(handleOperator(operator, value2, value1));
        } catch (InvalidOperatorException e) {
            e.printStackTrace();
        }
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
