package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the list of daily tasks.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/03/2018
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriority2() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("5", 5));
        queue.put(new Task("1", 1));
        queue.put(new Task("3", 3));
        queue.put(new Task("400", 400));
        queue.put(new Task("10", 10));
        queue.put(new Task("15", 15));
        queue.put(new Task("55", 55));
        queue.put(new Task("11", 11));
        queue.put(new Task("0", 0));
        queue.put(new Task("5000", 5000));
        queue.put(new Task("100", 100));
        queue.put(new Task("3", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("0"));
    }

    @Test
    public void whenListIsEmpty() {
        PriorityQueue queue = new PriorityQueue();
        Task result = queue.take();
        if (result != null) {
            System.out.println(result.toString());
        }
    }
}