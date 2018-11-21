package ru.job4j.monitore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Shows the modifying of model by two threads. Modifications produced alternately.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 16/11/2018
 */
public class SwitcherMain {
    public static void main(String[] args) {
        Lock switcher = new ReentrantLock(true);
        Condition isBusy = switcher.newCondition();
        Model model = new Model("");

        Thread t0 = new Thread(new Modifier(model, 1, switcher, isBusy));
        t0.start();
        Thread t1 = new Thread(new Modifier(model, 2, switcher, isBusy));
        t1.start();
    }
}