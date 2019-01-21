package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing 'Counter'.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 18/01/2018
 * @version 1.0
 */

public class CounterTest {
    @Test
    public void whenStartIsOneAndFinishIsTenThanThirty() {
        Counter count = new Counter();
        int result = count.add(1, 10);
        int expected = 30;
        assertThat(result, is(expected));
    }

    @Test
    public void whenStartIs18AndFinishIs22Than60() {
        Counter count = new Counter();
        int result = count.add(18, 22);
        int expected = 60;
        assertThat(result, is(expected));
    }
}