package ru.job4j.blckqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Parallel Search.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 25/08/2018
 */
public class ParallelSearch {
    public static void main(String[] args) {
        int size = 10;
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(size);
        final Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.poll());
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    Thread.currentThread().interrupt();
                    System.out.println("Consumer interrupted.");
                    break;
                }
            }
        });
        consumer.start();
        new Thread(() -> {
            for (int index = 0; index != 3; index++) {
                try {
                    queue.offer(index);
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            consumer.interrupt();
        }).start();
    }
}