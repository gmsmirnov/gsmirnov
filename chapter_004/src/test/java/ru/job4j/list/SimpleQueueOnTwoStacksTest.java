package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple queue based on two stacks with generic type test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 16/09/2018
 */
public class SimpleQueueOnTwoStacksTest {
    private final SimpleQueueOnTwoStacks<Integer> queue = new SimpleQueueOnTwoStacks<Integer>();

    @Test
    public void whenPushInDirectOrderThenPollInDirectOrder() {
        int size = 10;
        for (int i = 0; i < size; i++) {
            this.queue.push(i);
        }
        for (int i = 0; i < size; i++) {
            assertThat(this.queue.poll(), is(i));
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenPushAndPollInDirectOrderAlternateThenPollInDirectOrder() {
        for (int i = 0; i < 10; i++) {
            this.queue.push(i);
        }
        assertThat(this.queue.poll(), is(0));
        assertThat(this.queue.poll(), is(1));
        assertThat(this.queue.poll(), is(2));
        assertThat(this.queue.poll(), is(3));
        assertThat(this.queue.poll(), is(4));
        assertThat(this.queue.poll(), is(5));
        for (int i = 10; i < 20; i++) {
            this.queue.push(i);
        }
        assertThat(this.queue.poll(), is(6));
        assertThat(this.queue.poll(), is(7));
        assertThat(this.queue.poll(), is(8));
        assertThat(this.queue.poll(), is(9));
        assertThat(this.queue.poll(), is(10));
        assertThat(this.queue.poll(), is(11));
        assertThat(this.queue.poll(), is(12));
        assertThat(this.queue.poll(), is(13));
        assertThat(this.queue.poll(), is(14));
        assertThat(this.queue.poll(), is(15));
        assertThat(this.queue.poll(), is(16));
        assertThat(this.queue.poll(), is(17));
        assertThat(this.queue.poll(), is(18));
        assertThat(this.queue.poll(), is(19));
        assertThat(this.queue.poll(), is(20));
    }
}