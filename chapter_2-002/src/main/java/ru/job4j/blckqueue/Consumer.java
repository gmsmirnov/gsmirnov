package ru.job4j.blckqueue;

/**
 * The consumer. Gets elements from the queue.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/08/2018
 */
public class Consumer implements Runnable {
    /**
     * The default lifecycle.
     */
    private static final int DEFAULT_LIFECYCLE = 10;

    /**
     * The simple blocking queue.
     */
    private final SimpleBlockingQueue<Integer> queue;

    /**
     * The quantity of cycles.
     */
    private final int lifeCycle;

    /**
     * Creates consumer and starts thread with default life cycle (10).
     *
     * @param queue - the simple blocking queue.
     */
    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
        this.lifeCycle = Consumer.DEFAULT_LIFECYCLE;
        new Thread(this, "DEFAULT CONSUMER").start();
    }

    /**
     * Creates consumer with a specified lifecycle and starts thread.
     *
     * @param queue - the simple blocking queue.
     * @param lifeCycle - quantity of cycles.
     */
    public Consumer(SimpleBlockingQueue<Integer> queue, int lifeCycle) {
        this.queue = queue;
        this.lifeCycle = lifeCycle;
        new Thread(this, "CONSUMER").start();
    }

    /**
     * Thread's body. All the time try to get the first element from the queue.
     */
    @Override
    public void run() {
        System.out.printf("Consumer thread [%s] started.%n", Thread.currentThread().getName());
        for (int cycle = 0; cycle < this.lifeCycle; cycle++) {
            try {
                this.queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}