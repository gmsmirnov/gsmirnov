package ru.job4j.pool;

import ru.job4j.blckqueue.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

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
    private final SimpleBlockingQueue<Runnable> tasks;

    /**
     * The pool's size.
     */
    private final int size;

    /**
     * The default task's queue size.
     */
    private final static int QUEUE_DEFAULT_SIZE = 10;

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
        this.size = poolSize;
        this.tasks = new SimpleBlockingQueue<Runnable>(queueSize);
        for (int index = 0; index < this.size; index++) {
            this.threads.add(new Thread(() -> {
                while (!this.tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                    try {
                        ThreadPool.this.tasks.poll().run();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                        Thread.currentThread().interrupt();
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
        try {
            this.tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the work of thread pool.
     */
    public void shutdown() {
        for (int index = 0; index < this.size; index++) {
            this.threads.get(index).interrupt();
        }
    }
}