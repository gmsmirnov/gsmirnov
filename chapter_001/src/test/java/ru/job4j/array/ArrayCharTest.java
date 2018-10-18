package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing 'ArrayChar' for starts with the prefix.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 04/07/2018
 * @version 1.0
 */
public class ArrayCharTest {
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }

    @Test
    public void whenPrefixIsLongerThenDataThenFalse() {
        ArrayChar word = new ArrayChar("");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }
}