package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing 'Matrix'.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 23/01/2018
 * @version 1.0
 */
public class MatrixTest {
    @Test
    public void when5Multiple8Than40() {
        Matrix m = new Matrix();
        int size = 10;
        int[][] matrix = m.multiple(size);
        int expected = 5 * 8;
        int result = matrix[5][8];
        assertThat(result, is(expected));
    }

    @Test
    public void when9Multiple6Than54() {
        Matrix m = new Matrix();
        int size = 10;
        int[][] matrix = m.multiple(size);
        int expected = 9 * 6;
        int result = matrix[9][6];
        assertThat(result, is(expected));
    }
}