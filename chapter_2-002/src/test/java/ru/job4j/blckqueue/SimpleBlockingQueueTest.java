package ru.job4j.blckqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    @Test
    public void test() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);
        Thread prod = new Thread(new Producer(queue));
        Thread cons = new Thread(new Consumer(queue));
        try {
            prod.join();
            cons.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<Integer>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(3);
        Thread producer = new Thread(() -> IntStream.range(0, 5).forEach(value -> {
            try {
                queue.offer(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        producer.start();
        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    buffer.add(queue.poll());
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}