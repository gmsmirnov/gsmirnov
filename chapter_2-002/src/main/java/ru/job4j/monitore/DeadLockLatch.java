package ru.job4j.monitore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dead lock with CountDownLatch. After countdown threads stars locking
 * the second lock which is already held by another thread.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/11/2018
 */
public class DeadLockLatch implements Runnable {
    /**
     * Latch parameter.
     */
    public static final int QUANTITY = 2;

    /**
     * The latch.
     */
    private final CountDownLatch latch;

    /**
     * The first lock.
     */
    private final Lock lock0;

    /**
     * The second lock.
     */
    private final Lock lock1;

    /**
     * The constructor.
     *
     * @param lock0 - the first lock.
     * @param lock1 - the second lock.
     * @param latch - the latch.
     */
    public DeadLockLatch(Lock lock0, Lock lock1, CountDownLatch latch) {
        this.lock0 = lock0;
        this.lock1 = lock1;
        this.latch = latch;
    }

    /**
     * A thread's action.
     */
    @Override
    public void run() {
        this.lock0.lock();
        try {
            this.latch.countDown();
            try {
                this.latch.await();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            this.lock1.lock();
            try {
                System.out.println("This string will never be printed");
            } finally {
                this.lock1.unlock();
            }
        } finally {
            this.lock0.unlock();
        }
    }

    /**
     * The main deadlock action.
     * @param args - params.
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(DeadLockLatch.QUANTITY);
        Lock lock0 = new ReentrantLock();
        Lock lock1 = new ReentrantLock();
        Thread t0 = new Thread(new DeadLockLatch(lock0, lock1, latch));
        Thread t1 = new Thread(new DeadLockLatch(lock1, lock0, latch));
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }
}