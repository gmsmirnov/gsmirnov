package ru.job4j.blckqueue;

/**
 * Parallel Search.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 25/08/2018
 */
public class ParallelSearch {
    public static void main(String[] args) {
        int size = 2;
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(size);
        final Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(queue.poll());
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                    Thread.currentThread().interrupt();
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