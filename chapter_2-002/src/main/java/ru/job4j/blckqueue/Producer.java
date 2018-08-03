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
     * The simple blocking queue.
     */
    private final SimpleBlockingQueue<Integer> queue;

    /**
     * Creates producer and starts thread.
     *
     * @param queue - the simple blocking queue.
     */
    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
        new Thread(this, "PRODUCER").start();
    }

    /**
     * Thread's body. All the time try to put new element to the queue.
     */
    @Override
    public void run() {
        System.out.printf("Producer thread [%s] started.%n", Thread.currentThread().getName());
        while (true) {
            try {
                this.queue.offer((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}