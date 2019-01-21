package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing elementary calculator.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 12/01/2018
 * @version 1.0
 */
public class CalculatorTest {
    @Test
    public void whenAddTwoPlusThreeThanFive() {
        Calculator calc = new Calculator();
        calc.add(2.0, 3.0);
        double result = calc.getResult();
        double expected = 5.0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractFourMinusTwoThanTwo() {
        Calculator calc = new Calculator();
        calc.subtract(4.0, 2.0);
        double result = calc.getResult();
        double expected = 2.0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultipleTreeAndTwoThanSix() {
        Calculator calc = new Calculator();
        calc.multiple(3.0, 2.0);
        double result = calc.getResult();
        double expected = 6.0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivEightDevidedTwoThanFour() {
        Calculator calc = new Calculator();
        calc.div(8.0, 2.0);
        double result = calc.getResult();
        double expected = 4.0;
        assertThat(result, is(expected));
    }

}