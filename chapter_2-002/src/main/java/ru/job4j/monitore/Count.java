package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Simple counter.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/07/2018
 */
@ThreadSafe
public class Count {
    /**
     * The value.
     */
    @GuardedBy("this")
    private int value;

    /**
     * Increments value.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Gets value.
     *
     * @return the value.
     */
    public synchronized int get() {
        return this.value;
    }
}