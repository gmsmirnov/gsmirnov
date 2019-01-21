package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing 'Factorial'.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 18/01/2018
 * @version 1.1
 */
public class FactorialTest {
    @Test
    public void whenFactorialFiveThanOneHundredTwenty() {
        Factorial fact = new Factorial();
        int result = fact.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }

    @Test
    public void whenFactorial9Than362880() {
        Factorial fact = new Factorial();
        int result = fact.calc(9);
        int expected = 362880;
        assertThat(result, is(expected));
    }

    @Test
    public void whenFactorialMinusOneThanException() {
        Factorial fact = new Factorial();
        try {
            int result = fact.calc(-1);
            int expected = -1;
            assertThat(result, is(expected));
        } catch (IllegalArgumentException e) {
            System.out.println("Bad argument: " + e.getMessage());
        }
    }

    @Test
    public void whenFactorialZeroThanOne() {
        Factorial fact = new Factorial();
        int result = fact.calc(0);
        int expected = 1;
        assertThat(result, is(expected));
    }
}