package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple queue based on two stacks with generic type test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 16/09/2018
 */
public class SimpleQueueOnTwoStacksTest {
    private final SimpleQueueOnTwoStacks<Integer> queue = new SimpleQueueOnTwoStacks<Integer>();

    @Test
    public void whenPushInDirectOrderThenPollInDirectOrder() {
        this.queue.push(0);
        this.queue.push(1);
        this.queue.push(2);
        assertThat(this.queue.poll(), is(0));
        assertThat(this.queue.poll(), is(1));
        assertThat(this.queue.poll(), is(2));
    }

    @Test
    public void whenPushAndPollInDirectOrderAlternate1() {
        this.queue.push(0);
        assertThat(this.queue.poll(), is(0));
        this.queue.push(1);
        this.queue.push(2);
        assertThat(this.queue.poll(), is(1));
        assertThat(this.queue.poll(), is(2));
    }

    @Test
    public void whenPushAndPollInDirectOrderAlternate2() {
        this.queue.push(0);
        this.queue.push(1);
        assertThat(this.queue.poll(), is(0));
        this.queue.push(2);
        assertThat(this.queue.poll(), is(1));
        assertThat(this.queue.poll(), is(2));
    }

    @Test
    public void whenPushAndPollInDirectOrderAlternate3() {
        this.queue.push(0);
        this.queue.push(1);
        assertThat(this.queue.poll(), is(0));
        assertThat(this.queue.poll(), is(1));
        this.queue.push(2);
        assertThat(this.queue.poll(), is(2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenPollFromEmptyQueue() {
        assertThat(this.queue.poll(), is(0));
    }
}