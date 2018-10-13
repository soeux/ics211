package edu.ics211.h07;

import java.util.Stack;

/**
 * what does it do?
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

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            // check if number: push to stack
            // check if operator: calculate
            // else: invalid expression exception
            if (isNumber(token)) {
                calcStack.push(convertNumber(token));
            } else if (isOperator(token)) {
                calculate(token);
            } else {
                throw new InvalidExpressionException();
            }
        }

        Number answer = calcStack.pop();
        if (!calcStack.empty()) {
            throw new InvalidExpressionException();
        }

        return answer;
    }

    /**
     *
     * @param token
     */
    private void calculate(String token) {
        // FILO
        Number second = calcStack.pop();
        Number first = calcStack.pop();

        // check if any of the numbers are Doubles
        // if one of them is Double, then all numbers have to be Double.
        if (second instanceof Double || first instanceof Double) {
            Double one = first.doubleValue();
            Double two = second.doubleValue();
            Double result = null;

            switch(token) {
                case "+":
                    result = one + two;
                case "-":
                    result = one - two;
                case "*":
                    result = one * two;
                case "/":
                    result = one / two;
                default:
                    break;
            }

            calcStack.push(result);
        } else {
            // if there are no Doubles, do Integer math.
            Integer one = first.intValue();
            Integer two = second.intValue();
            Integer result = null;

            switch(token) {
                case "+":
                    result = one + two;
                case "-":
                    result = one - two;
                case "*":
                    result = one * two;
                case "/":
                    result = one / two;
                default:
                    break;
            }

            calcStack.push(result);
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
     *
     * @param token value we're checking.
     * @return true if it's an operator; false otherwise.
     */
    private boolean isOperator(String token) {
        switch (token) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks a string is a number with regex.
     *
     * @param token value we're checking.
     * @return true if number; false otherwise.
     */
    private boolean isNumber(String token) {
        // # credit: https://regexr.com/4113k
        return token.matches("-?\\.?\\d+(\\.\\d+)?");
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
        throw new UnsupportedOperationException();
    }
}
