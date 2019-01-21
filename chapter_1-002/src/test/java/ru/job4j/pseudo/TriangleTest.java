package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests Triangle.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/02/2018
 */
public class TriangleTest {
    @Test
    public void whqnDrawsTriangle() {
        String result = new Triangle(3).pic();
        String expected = new StringBuilder()
                .append("  +  ").append(System.lineSeparator())
                .append(" +++ ").append(System.lineSeparator())
                .append("+++++").append(System.lineSeparator())
                .toString();
        assertThat(result, is(expected));
    }
}