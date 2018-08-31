package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

/**
 * The simple thread pool test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 31/08/2018
 */
public class ThreadPoolTest {
    private final ThreadPool pool = new ThreadPool();

    @Test
    public void whenQueueNotBlocked() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            this.pool.work(this.getTask("hello!"));
            Thread.sleep(10);
        }
        Thread.sleep(1000);
        assertThat(this.pool.getBlockCounter().get(), is(0));
    }

    @Test
    public void whenQueueIsBlocked() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            this.pool.work(this.getTask("hello!"));
            Thread.sleep(0);
        }
        Thread.sleep(1000);
        assertThat(this.pool.getBlockCounter().get(), greaterThan(0));
    }

    private Runnable getTask(String message) {
        return () -> System.out.printf("Message: \'%s\' from thread: %s%n", message, Thread.currentThread());
    }
}