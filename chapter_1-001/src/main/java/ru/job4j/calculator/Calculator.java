package ru.job4j.calculator;

/**
 * Elementary calculator.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 14/01/2018
 * @version 1.0
 */
public class Calculator {
    private double result;

    /**
     * Addition.
     * @param firstAddend - the first addend.
     * @param secondAddend - the second addend.
     */
    public void add(double firstAddend, double secondAddend) {
        this.result = firstAddend + secondAddend;
    }

    /**
     * Subtraction.
     * @param minuend - the minuend.
     * @param subtrahend - the subtrahend.
     */
    public void subtract(double minuend, double subtrahend) {
        this.result = minuend - subtrahend;
    }

    /**
     * Multiplication.
     * @param multiplicand - the multiplicand.
     * @param multiplier - the multiplier.
     */
    public void multiple(double multiplicand, double multiplier) {
        this.result = multiplicand * multiplier;
    }

    /**
     * Division.
     * @param dividend - the dividend.
     * @param divisor - the divisor.
     */
    public void div(double dividend, double divisor) {
        this.result = dividend / divisor;
    }

    /**
     * Getting result of mathematical operation.
     * @return result of mathematical operation.
     */
    public double getResult() {
        return this.result;
    }
}