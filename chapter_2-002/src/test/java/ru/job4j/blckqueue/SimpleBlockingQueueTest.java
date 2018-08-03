package ru.job4j.blckqueue;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);
        Thread prod = new Thread(new Producer(queue));
        Thread cons = new Thread(new Consumer(queue));
    }
}