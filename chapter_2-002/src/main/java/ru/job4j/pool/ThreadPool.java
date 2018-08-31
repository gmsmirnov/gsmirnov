package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The simple thread pool.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 31/08/2018
 */
public class ThreadPool {
    /**
     * The pool.
     */
    private final List<Thread> threads = new LinkedList<Thread>();

    /**
     * The task's queue.
     */
    private final LinkedBlockingQueue<Runnable> tasks;

    /**
     * The pool's size.
     */
    private final int size;

    /**
     * The default task's queue size.
     */
    private final static int QUEUE_DEFAULT_SIZE = 10;

    /**
     * The counter for blocked tasks. Increments when the task's queue is full and thread pool tries to put into
     * the queue new task.
     */
    private final AtomicInteger blockCounter;

    /**
     * The group for this thread pool threads.
     */
    private ThreadGroup group = new ThreadGroup("Thread Pool");

    /**
     * The default constructor. With size of thread pool defined by processor's cores and default task's queue size.
     */
    public ThreadPool() {
        this(Runtime.getRuntime().availableProcessors(), ThreadPool.QUEUE_DEFAULT_SIZE);
    }

    /**
     * The constructor with the specified task's queue size.
     *
     * @param queueSize - the specified task's queue size.
     */
    public ThreadPool(int queueSize) {
        this(Runtime.getRuntime().availableProcessors(), queueSize);
    }

    /**
     * The constructor with the specified thread pool size and the specified task's queue size.
     *
     * @param poolSize - the specified thread pool size.
     * @param queueSize - the specified task's queue size.
     */
    public ThreadPool(int poolSize, int queueSize) {
        this.blockCounter = new AtomicInteger(0);
        this.size = poolSize;
        this.tasks = new LinkedBlockingQueue<Runnable>(queueSize);
        for (int index = 0; index < this.size; index++) {
            this.threads.add(new Thread(this.group, () -> {
                while (true) {
                    try {
                        Runnable task = ThreadPool.this.tasks.take();
                        task.run();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                        break;
                    }
                }
            }));
            this.threads.get(index).start();
        }
    }

    /**
     * This method tries to put new task to the task's queue. When queue is full, increments blocked counter.
     *
     * @param job - new task.
     */
    public void work(Runnable job) {
        if (!this.tasks.offer(job)) {
            System.out.println("The queue is full!");
            this.blockCounter.getAndIncrement();
        }
    }

    /**
     * Stops the work of thread pool.
     */
    public void shutdown() {
        for (int index = 0; index < this.size; index++) {
            this.threads.get(index).interrupt();
            System.out.printf("Thread: %s was interrupted.%n", this.threads.get(index));
        }
        Thread.currentThread().interrupt();
    }

    /**
     * Gets the blocked counter.
     *
     * @return - the quantity of blocked tasks.
     */
    public AtomicInteger getBlockCounter() {
        return this.blockCounter;
    }
}