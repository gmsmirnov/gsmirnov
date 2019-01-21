package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Class for testing distance calculation between two points.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 15/01/2018
 * @version 1.0
 */
public class PointTest {
    @Test
    public void whenAddTwoPlusThreeThanFive() {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        double result = a.distance(b);
        double expected = 4.4;
        assertThat(result, closeTo(expected, 0.1));
    }
}
