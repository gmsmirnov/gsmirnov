package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing search of maximum value.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 15/01/2018
 * @version 1.0
 */
public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max maximum = new Max();
        int maxValue = maximum.max(1, 2);
        assertThat(maxValue, is(2));
    }

    @Test
    public void whenFirstMoreSecond() {
        Max maximum = new Max();
        int maxValue = maximum.max(3, 2);
        assertThat(maxValue, is(3));
    }

    @Test
    public void whenFirstEqualsSecond() {
        Max maximum = new Max();
        int maxValue = maximum.max(3, 3);
        assertThat(maxValue, is(3));
    }
}