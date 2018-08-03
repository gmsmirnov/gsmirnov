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
     * The simple blocking queue.
     */
    private final SimpleBlockingQueue<Integer> queue;

    /**
     * Creates consumer and starts thread.
     *
     * @param queue - the simple blocking queue.
     */
    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
        new Thread(this, "CONSUMER").start();
    }

    /**
     * Thread's body. All the time try to get the first element from the queue.
     */
    @Override
    public void run() {
        System.out.printf("Consumer thread [%s] started.%n", Thread.currentThread().getName());
        while (true) {
            try {
                this.queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}