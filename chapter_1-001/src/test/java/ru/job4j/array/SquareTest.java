package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Square array test.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 21/01/2018
 * @version 1.0
 */
public class SquareTest {
    @Test
    public void whenBoundIs5ThanLastArrayElementIs25() {
        Square square = new Square();
        int[] expected = {1, 4, 9, 16, 25};
        int[] result = square.calculate(5);
        assertThat(result, is(expected));
    }

    @Test
    public void whenBoundIsLoverThan0ThanException() {
        Square square = new Square();
        int[] expected = {1, 4, 9, 16, 25};
        try {
            int[] result = square.calculate(-1);
            assertThat(result, is(expected));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}