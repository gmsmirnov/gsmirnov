package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple stack test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/05/2018
 */
public class SimpleStackTest {
    private SimpleStack<Integer> stack;

    @Before
    public void init() {
        this.stack = new SimpleStack<Integer>();
        for (int i = 0; i < 5; i++) {
            this.stack.push(i);
        }
    }

    @Test
    public void whenPollThenGetElementsInLIFOOrder() {
        assertThat(this.stack.poll(), is(4));
        assertThat(this.stack.poll(), is(3));
        assertThat(this.stack.poll(), is(2));
        assertThat(this.stack.poll(), is(1));
        assertThat(this.stack.poll(), is(0));
        Iterator<Integer> it = this.stack.iterator();
        assertThat(it.hasNext(), is(false));
    }
}