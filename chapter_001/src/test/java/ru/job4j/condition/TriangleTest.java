package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Class for testing triangle area calculation.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 16/01/2018
 * @version 1.0
 */
public class TriangleTest {
    @Test
    public void whenAreaSetThreePointsThanTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 2.0;
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void whenAreaSetThreePointsThanTriangleArea2() {
        Point a = new Point(1, 1);
        Point b = new Point(-2, 4);
        Point c = new Point(-2, -2);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 9.0;
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void whenTriangleNotExist() {
        Point a = new Point(0, 0);
        Point b = new Point(100, 0);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = -1.0;
        assertThat(result, closeTo(expected, 0.1));
    }
}