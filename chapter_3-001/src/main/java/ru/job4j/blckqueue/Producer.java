package ru.job4j.blckqueue;

/**
 * The producer. Puts new elements to the blocking queue.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/08/2018
 */
public class Producer implements Runnable {
    /**
     * The default lifecycle.
     */
    private static final int DEFAULT_LIFECYCLE = 20;

    /**
     * The simple blocking queue.
     */
    private final SimpleBlockingQueue<Integer> queue;

    /**
     * The quantity of cycles.
     */
    private final int lifeCycle;

    /**
     * Creates producer and starts thread with default life cycle (10).
     *
     * @param queue - the simple blocking queue.
     */
    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
        this.lifeCycle = Producer.DEFAULT_LIFECYCLE;
        new Thread(this, "DEFAULT PRODUCER").start();
    }

    /**
     * Creates producer with a specified lifecycle and starts thread.
     *
     * @param queue - the simple blocking queue.
     * @param lifeCycle - quantity of cycles.
     */
    public Producer(SimpleBlockingQueue<Integer> queue, int lifeCycle) {
        this.queue = queue;
        this.lifeCycle = lifeCycle;
        new Thread(this, "PRODUCER").start();
    }

    /**
     * Thread's body. All the time try to put new element to the queue.
     */
    @Override
    public void run() {
        System.out.printf("Producer thread [%s] started.%n", Thread.currentThread().getName());
        for (int cycle = 0; cycle < this.lifeCycle; cycle++) {
            try {
                this.queue.offer((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}