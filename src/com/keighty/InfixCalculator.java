package com.keighty;

import java.util.Stack;

public class InfixCalculator {
    private Stack operatorStack = new Stack<Character>();
    private Stack operandStack = new Stack<Integer>();
    private boolean popStack;
    private boolean popFullStack;

    public int calculate(String expression) throws InvalidOperatorException, InvalidInputException {
        if (expression.isEmpty()) throw new InvalidInputException("Invalid input: empty string");
        popStack = false;
        populateStacks(expression);
        evaluateStacks();
        return (int) operandStack.pop();
    }

    private void populateStacks(String expression) throws InvalidOperatorException {
        boolean seenNumeric = false;
        char[] charArr = expression.toCharArray();
        for (char item :
                charArr) {
            if (Character.isDigit(item)) {
                if (! seenNumeric) {
                    handleSingleDigitChar(item);
                    seenNumeric = true;
                } else {
                    handleMultiDigitChar(item);
                }
            } else if (! Character.isSpaceChar(item)) {
                seenNumeric = false;
                if (popStack || popFullStack) {
                    evaluateExpression();
                    popStack = false;
                }
                handleOperatorCharacter(item);
                operatorStack.push(item);
            }
        }
    }

    private void handleSingleDigitChar(char item) {
        operandStack.push(Character.getNumericValue(item));
    }

    private void handleMultiDigitChar(char item) {
        int prevInt = (int) operandStack.pop();
        String newInt = String.valueOf(prevInt) + String.valueOf(item);
        operandStack.push(Integer.valueOf(newInt));
    }

    private void handleOperatorCharacter(char operator) throws InvalidOperatorException {
        switch (operator) {
            case '*':case '/':
                popStack = true;
                break;
            case '+':case '-':
                popStack = false;
                break;
            case '(':
                popFullStack = false;
                break;
            case ')':
                popFullStack = true;
                break;
            default:
                throw new InvalidOperatorException("invalid input: " + operator);
        }
    }

    private void evaluateStacks() {
        while ( !operatorStack.isEmpty() ) {
            evaluateExpression();
        }
    }

    private void evaluateExpression() {
        if (operandStack.isEmpty()) throw new UnsupportedOperationException("no operand available");

        char operator = (char) operatorStack.pop();
        if (operator == ')' || operator == '(') return;
        int value1 = (int) operandStack.pop();
        int value2 = (int) operandStack.pop();
        try {
            operandStack.push(processOperator(operator, value2, value1));
        } catch (InvalidOperatorException e) {
            e.printStackTrace();
        }
    }

    private int processOperator(char operator, int operand1, int operand2) throws InvalidOperatorException {
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
