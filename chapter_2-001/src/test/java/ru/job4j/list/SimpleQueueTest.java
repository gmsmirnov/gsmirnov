package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple queue test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/05/2018
 */
public class SimpleQueueTest {
    private SimpleQueue<Integer> queue;

    @Before
    public void init() {
        this.queue = new SimpleQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            this.queue.push(i);
        }
    }

    @Test
    public void whenPollThenGetElementsInFIFOOrder() {
        assertThat(this.queue.poll(), is(0));
        assertThat(this.queue.poll(), is(1));
        assertThat(this.queue.poll(), is(2));
        assertThat(this.queue.poll(), is(3));
        assertThat(this.queue.poll(), is(4));
        Iterator<Integer> it = this.queue.iterator();
        assertThat(it.hasNext(), is(false));
    }
}