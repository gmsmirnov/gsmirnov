package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Turning array test.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 22/01/2018
 * @version 1.0
 */
public class TurnTest {
    @Test
    public void whenArrayContainsOddNumberOfElements() {
        Turn turn = new Turn();
        int[] expected = {25, 16, 9, 4, 1};
        int[] result = turn.back(new int[]{1, 4, 9, 16, 25});
        assertThat(result, is(expected));
    }

    @Test
    public void whenArrayContainsEvenNumberOfElements() {
        Turn turn = new Turn();
        int[] expected = {3, 2, 1, 0};
        int[] result = turn.back(new int[]{0, 1, 2, 3});
        assertThat(result, is(expected));
    }
}