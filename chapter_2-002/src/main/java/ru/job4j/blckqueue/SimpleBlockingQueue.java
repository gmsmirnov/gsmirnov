package ru.job4j.blckqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple blocking queue.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/08/2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * The size limit.
     */
    private final int limit;

    /**
     * The queue, based on Linked List.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<T>();

    /**
     * Creates queue with the specified size limit.
     *
     * @param limit - the specified size limit.
     */
    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    /**
     * Puts new element to the end of the queue, if the queue is full, than waits.
     *
     * @param value - a new element.
     * @throws InterruptedException
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            this.wait();
        }
        this.queue.offer(value);
        System.out.println("Put " + value + " into queue.");
        this.notifyAll();
    }

    /**
     * Gets the first element from the queue, if the queue is empty, than waits.
     *
     * @return the first element.
     * @throws InterruptedException
     */
    public synchronized T poll() throws InterruptedException {
        while (this.queue.size() == 0) {
            this.wait();
        }
        T result = this.queue.poll();
        System.out.println("Get " + result + " from queue.");
        this.notifyAll();
        return result;
    }
}