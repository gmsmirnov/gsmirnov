package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Find Loop test.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 22/01/2018
 * @version 1.0
 */
public class FindLoopTest {
    @Test
    public void whenElementExistsInArray() {
        Square square = new Square();
        FindLoop findLoop = new FindLoop();
        int expected = 3;
        int result = findLoop.indexOf(square.calculate(5), 16);
        assertThat(result, is(expected));
    }

    @Test
    public void whenElementNotExistsInArray() {
        Square square = new Square();
        FindLoop findLoop = new FindLoop();
        int expected = -1;
        int result = findLoop.indexOf(square.calculate(5), 15);
        assertThat(result, is(expected));
    }
}