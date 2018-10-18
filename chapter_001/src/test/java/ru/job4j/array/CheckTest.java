package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing 'Check' for boolean array.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 04/07/2018
 * @version 1.0
 */
public class CheckTest {
    @Test
    public void whenDataMonoByTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    @Test
    public void whenDataHasOneElementThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataHasNoElementsThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}