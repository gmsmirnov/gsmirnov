package ru.job4j.pool;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * The simple thread pool test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 31/08/2018
 */
public class ThreadPoolTest {
    private final ThreadPool pool = new ThreadPool();

    private final List<Integer> input = new CopyOnWriteArrayList<Integer>();

    private final List<Integer> output = new CopyOnWriteArrayList<Integer>();

    private final List<Integer> expected = new CopyOnWriteArrayList<Integer>();

    @Test
    public void whenNumberSquaringThenTrue() {
        IntStream.range(0, 100).forEach(this.input::add);
        for (Integer number : this.input) {
            this.pool.work(this.getTask(number));
        }
        for (Integer number : this.input) {
            this.expected.add(number * number);
        }
        assertThat(this.output.toArray(), arrayContainingInAnyOrder(this.expected.toArray()));
    }

    private Runnable getTask(int number) {
        return () -> this.output.add(number * number);
    }
}