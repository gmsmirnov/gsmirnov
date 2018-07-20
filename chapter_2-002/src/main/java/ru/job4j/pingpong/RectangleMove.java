package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Little rectangle's movement.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 20/07/2018
 */
public class RectangleMove implements Runnable {
    /**
     * the little rectangle.
     */
    private final Rectangle rect;

    /**
     * The movement delta.
     */
    private int delta = 1;

    /**
     * The constructor. Creates moving little rectangle gotten from the main thread.
     *
     * @param rect - the little rectangle gotten from the main thread.
     */
    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Movement's thread.
     */
    @Override
    public void run() {
        while (true) {
            if (this.rect.getX() == 300 || this.rect.getX() == 0) {
                this.changeDelta();
            }
            this.rect.setX(this.rect.getX() + this.delta);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for changing delta.
     */
    private void changeDelta() {
        this.delta *= -1;
    }
}