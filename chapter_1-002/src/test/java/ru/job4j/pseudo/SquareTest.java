package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests Square.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/02/2018
 */
public class SquareTest {
    @Test
    public void whenDrawsSquare() {
        String result = new Square(3).pic();
        String expected = new StringBuilder()
                .append("+++").append(System.lineSeparator())
                .append("+++").append(System.lineSeparator())
                .append("+++").append(System.lineSeparator())
                .toString();
        assertThat(result, is(expected));
    }
}