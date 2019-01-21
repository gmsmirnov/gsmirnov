package ru.job4j.blckqueue;

/**
 * Main.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/08/2018
 */
public class Main {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);
        new Producer(queue);
        new Consumer(queue);
    }
}