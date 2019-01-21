package ru.job4j.bomberman;

/**
 * The step description. Defines the delta X and delta Y of next character's step.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/10/2018
 */
public final class Step {
    /**
     * The delta X of the next step.
     */
    private final int deltaX;

    /**
     * The delta Y of the next step.
     */
    private final int deltaY;

    /**
     * The constructor, which creates the next step in random direction.
     */
    public Step() {
        int choice = (int) (Math.random() * Constants.MAX_DIRECTIONS);
        if (choice == 0) {
            this.deltaX = 1;
            this.deltaY = 0;
        } else if (choice == 1) {
            this.deltaX = 0;
            this.deltaY = 1;
        } else if (choice == 2) {
            this.deltaX = -1;
            this.deltaY = 0;
        } else {
            this.deltaX = 0;
            this.deltaY = -1;
        }
    }

    /**
     * Gets delta X of current step.
     *
     * @return delta X of current step.
     */
    public int getDeltaX() {
        return this.deltaX;
    }

    /**
     * Gets delta Y of current step.
     *
     * @return delta Y of current step.
     */
    public int getDeltaY() {
        return this.deltaY;
    }
}