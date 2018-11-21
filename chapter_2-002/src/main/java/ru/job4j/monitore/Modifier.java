package ru.job4j.monitore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * The thread which modifies specified model description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 16/11/2018
 */
public class Modifier implements Runnable {
    /**
     * The number of modifies in one round.
     */
    public static final int ROUND = 10;

    /**
     * The lock which switches threads.
     */
    private Lock switcher;

    /**
     * The modifier number.
     */
    private int modifier;

    /**
     * The model to modify.
     */
    private Model model;

    /**
     * The condition of switcher lock.
     */
    private Condition isBusy;

    /**
     * Modifier thread constructor.
     *
     * @param model - the specified model to modify.
     * @param modifier - the specified number to append to the model's data.
     * @param lock - the switcher lock.
     * @param isBusy - the condition of switcher lock.
     */
    public Modifier(Model model, int modifier, Lock lock, Condition isBusy) {
        this.model = model;
        this.modifier = modifier;
        this.switcher = lock;
        this.isBusy = isBusy;
    }

    /**
     * The thread's body. It modifies the model with the specified number in cycle.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                this.tryModify(this.model);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    /**
     * Modifies the model switching between threads.
     *
     * @param model - the model.
     * @throws InterruptedException if the current thread is interrupted.
     */
    private void tryModify(Model model) throws InterruptedException {
        this.switcher.lock();
        try {
            while (model.isBusy()) {
                this.isBusy.await();
            }
            model.setBusy(true);
            for (int counter = 0; counter < Modifier.ROUND; counter++) {
                this.model.modify(this.modifier);
            }
            System.out.println(this.model.getData());
            model.setBusy(false);
            this.isBusy.signal();
            Thread.sleep(500);
        } finally {
            this.switcher.unlock();
        }
    }
}