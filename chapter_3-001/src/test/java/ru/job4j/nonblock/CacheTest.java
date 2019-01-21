package ru.job4j.nonblock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The cache test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/08/2018
 */
public class CacheTest {
    private Cache cache = new Cache();

    @Before
    public void init() {
        this.cache.add(new Base(0));
        this.cache.add(new Base(1));
        this.cache.add(new Base(2));
        this.cache.add(new Base(3));
        this.cache.add(new Base(4));
    }

    @Test
    public void whenUpdatesAlreadyUpdatedModelThanExceptionSingleThread() {
        AtomicReference<Exception> ex = new AtomicReference<Exception>();
        this.cache.update(new Base(2, 2));
        try {
            this.cache.update(new Base(2, 2));
        } catch (OptimisticException oe) {
            ex.set(oe);
        }
        assertThat(ex.get().getMessage(), is("Current version is equal or higher!"));
    }

    @Test
    public void whenUpdatesAlreadyUpdatedModelThanExceptionParallelThread() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<Exception>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        UpdateTask task = new UpdateTask(this.cache, 2);
        this.cache.update(new Base(2, 1));
        try {
            Future<Base> futureResult = executor.submit(task);
            Base mod = futureResult.get();
        } catch (ExecutionException e) {
            if (e.getMessage().contains("Current version is equal or higher!")) {
                ex.set(e);
            }
        }
        assertThat(ex.get().getMessage(), is("ru.job4j.nonblock.OptimisticException: Current version is equal or higher!"));
    }

    @Test
    public void whenAddsNewModelsThanCacheHasThoseModels() {
        final AtomicInteger id = new AtomicInteger(5);
        int threadsQuantity = 20;
        Thread[] threads = new Thread[threadsQuantity];
        for (int index = 0; index < threadsQuantity; index++) {
            threads[index] = new Thread(() -> cache.add(new Base(id.getAndIncrement())));
            threads[index].start();
            try {
                threads[index].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int index = 5; index < 25; index++) {
            Assert.assertThat(this.cache.getModel(index), is(new Base(index)));
        }
    }

    @Test
    public void whenDeletesModelsThanCacheHasNotThoseModels() {
        for (int i = 0; i < 25; i++) {
            this.cache.add(new Base(i));
        }
        final AtomicInteger id = new AtomicInteger(0);
        int threadsQuantity = 30;
        Thread[] threads = new Thread[threadsQuantity];
        for (int index = 0; index < threadsQuantity; index++) {
            threads[index] = new Thread(() -> cache.delete(new Base(id.getAndIncrement())));
            threads[index].start();
            try {
                threads[index].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int index = 0; index < 30; index++) {
            Assert.assertThat(this.cache.getModel(index), is((Base) null));
        }
    }
}