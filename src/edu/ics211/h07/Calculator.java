package edu.ics211.h07;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Implements postfix and prefix calculator.
 *
 * @author Christian Mancha
 */
public class Calculator implements ICalculator {
    private static Calculator instance;
    private Stack<Number> calcStack = new Stack<Number>();

    /**
     * Calculator is a singleton class, so there can only be one instance of it.
     */
    private Calculator() {}

    /**
     * Check to see if Calculator exists. If not, create a new instance, if it does exist then do nothing.
     *
     * @return instance of Calculator.
     */
    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }

        return instance;
    }


    /**
     * Clears the calculator.
     */
    @Override
    public void clear() {
        while (!calcStack.empty()) {
            calcStack.pop();
        }
    }

    /**
     * Calculates the answer to the post-fix expression and returns it as a Number.
     *
     * @param expression the post-fix expression.
     * @return The answer as a Number.
     * @throws InvalidExpressionException if the expression is invalid.
     */
    @Override
    public Number postFixCalculate(String expression) throws InvalidExpressionException {
        // # credit: this entire method was taken from an example that was written in class [20181010]
        // split up the expression by spaces
        String[] tokens = expression.split("\\s+");

        // loop thru the expression
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            // check if number: push to stack
            // check if operator: calculate
            // else: invalid expression exception
            if (isNumber(token)) {
                calcStack.push(convertNumber(token));
            } else if (isOperator(token)) {
                calculatePostFix(token);
            } else {
                throw new InvalidExpressionException();
            }
        }

        // get the answer + check if everything was calculated
        Number answer = calcStack.pop();

        if (!calcStack.empty()) {
            throw new InvalidExpressionException();
        }

        return answer;
    }

    /**
     * Calculates the answer to the pre-fix expression and returns it as a Number.
     * This method is optional. You will get extra credit if you implement this method.
     * Otherwise just throw an UnsupportedOperationException.
     *
     * @param expression the pre-fix expression.
     * @return The answer as a Number.
     * @throws InvalidExpressionException if the expression is invalid.
     */
    @Override
    public Number preFixCalculate(String expression) throws InvalidExpressionException {
        // worry about this one later since it uses a different notation
        //throw new UnsupportedOperationException();

        // the way you implement this is the same as postfix, but backwards
        String[] tokens = expression.split("\\s+");

        // loop thru the expression
        calcStack.clear();
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            // check if number: push to stack
            // check if operator: calculate
            // else: invalid expression exception
            if (isNumber(token)) {
                calcStack.push(convertNumber(token));
            } else if (isOperator(token)) {
                calculatePreFix(token);
            } else {
                throw new InvalidExpressionException();
            }
        }

        // get the answer + check if everything was calculated
        Number answer = calcStack.pop();

        if (!calcStack.empty()) {
            throw new InvalidExpressionException();
        }

        return answer;
    }

    /**
     * Does actual postfix calculation.
     *
     * @param token The operator used to do the calculation.
     * @throws InvalidExpressionException If the expression was incorrect.
     */
    private void calculatePostFix(String token) throws InvalidExpressionException {
        // FILO
        // check if we have two operands to even do a calculation
        Number second, first;
        try {
            second = calcStack.pop();
            first = calcStack.pop();
        } catch (EmptyStackException e) {
            throw new InvalidExpressionException();
        }

        // check if any of the numbers are Doubles
        // if one operand is Double, then the entire calculation will be Double.
        if (second instanceof Double || first instanceof Double) {
            Double one = first.doubleValue();
            Double two = second.doubleValue();

            switch(token) {
                case "+":
                    calcStack.push(one + two);
                    break;
                case "-":
                    calcStack.push(one - two);
                    break;
                case "*":
                    calcStack.push(one * two);
                    break;
                case "/":
                    calcStack.push(one / two);
                    break;
                default:
                    break;
            }
        } else { // if there are no Doubles, do Integer math.
            Integer one = first.intValue();
            Integer two = second.intValue();

            switch(token) {
                case "+":
                    calcStack.push(one + two);
                    break;
                case "-":
                    calcStack.push(one - two);
                    break;
                case "*":
                    calcStack.push(one * two);
                    break;
                case "/":
                    calcStack.push(one / two);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Does actual prefix calculations.
     *
     * @param token The operator used to do the calculation.
     * @throws InvalidExpressionException If the expression is incorrect.
     */
    private void calculatePreFix(String token) throws InvalidExpressionException {
        // FILO
        // check if we have two operands to even do a calculation
        Number second, first;
        try {
            // because we're looping backwards, we have to change the order we pop the numbers off the stack
            first = calcStack.pop();
            second = calcStack.pop();
        } catch (EmptyStackException e) {
            throw new InvalidExpressionException();
        }

        // check if any of the numbers are Doubles
        // if one operand is Double, then the entire calculation will be Double.
        if (second instanceof Double || first instanceof Double) {
            Double one = first.doubleValue();
            Double two = second.doubleValue();

            switch(token) {
                case "+":
                    calcStack.push(one + two);
                    break;
                case "-":
                    calcStack.push(one - two);
                    break;
                case "*":
                    calcStack.push(one * two);
                    break;
                case "/":
                    calcStack.push(one / two);
                    break;
                default:
                    break;
            }
        } else { // if there are no Doubles, do Integer math.
            Integer one = first.intValue();
            Integer two = second.intValue();

            switch(token) {
                case "+":
                    calcStack.push(one + two);
                    break;
                case "-":
                    calcStack.push(one - two);
                    break;
                case "*":
                    calcStack.push(one * two);
                    break;
                case "/":
                    calcStack.push(one / two);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Converts input number from String to either Double or Integer depending if it has a decimal.
     *
     * @param token input number.
     * @return Double or Integer representation of token.
     */
    private Number convertNumber(String token) {
        // check if token is a double with regex + return as Number accordingly
        if (token.matches("\\.?\\d+(\\.\\d+)")) {
            return Double.parseDouble(token);
        } else {
            return Integer.parseInt(token);
        }
    }

    /**
     * Checks if a String is an operator.
     *
     * @param token value we're checking.
     * @return true if it's an operator; false otherwise.
     */
    private boolean isOperator(String token) {
        // what's better in this case? switch/case or regex?
        switch (token) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
        //return token.matches("[\\+\\-\\*\\/]]");
    }

    /**
     * Checks if a String is a number with regex.
     *
     * @param token value we're checking.
     * @return true if number; false otherwise.
     */
    private boolean isNumber(String token) {
        // # credit: https://regexr.com/4113k
        return token.matches("-?\\.?\\d+(\\.\\d+)?");
    }
}
