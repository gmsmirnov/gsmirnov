package ru.job4j.comparison;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Tests sorting strings by elements.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 18/03/2018
 */
public class ListCompareTest {
    @Test
    public void whenStringsAreEqualsThenZero() {
        ListCompare compare = new ListCompare();
        int result = compare.compare("Ivanov", "Ivanov");
        assertThat(result, is(0));
    }

    @Test
    public void whenLeftLessThenRightResultShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int result = compare.compare("Ivanov", "Ivanova");
        assertThat(result, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThenRightResultShouldBePositive() {
        ListCompare compare = new ListCompare();
        int result = compare.compare("Petrov", "Ivanova");
        assertThat(result, greaterThan(0));
    }

    @Test
    public void whenSecondCharOfLeftGreaterThenRightShouldBePositive() {
        ListCompare compare = new ListCompare();
        int result = compare.compare("Petrov", "Patrov");
        assertThat(result, greaterThan(0));
    }

    @Test
    public void whenSecondCharOfLeftLessThenRightShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int result = compare.compare("Patrova", "Petrov");
        assertThat(result, lessThan(0));
    }
}