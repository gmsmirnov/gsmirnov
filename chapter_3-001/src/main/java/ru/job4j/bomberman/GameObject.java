package ru.job4j.bomberman;

/**
 * Game object's description (hero, monster or block).
 * Each object has x and y coordinates.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/11/2018
 */
public abstract class GameObject {
    /**
     * The game object's X coordinate.
     */
    private final int x;

    /**
     * The game object's Y coordinate.
     */
    private final int y;

    /**
     * Creates new game object with the specified coordinates.
     *
     * @param x - the specified X coordinate.
     * @param y - the specified Y coordinate.
     */
    public GameObject(int x, int y) {
            this.x = x;
            this.y = y;
    }

    /**
     * Gets current X coordinate.
     *
     * @return current X coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets current Y coordinate.
     *
     * @return current Y coordinate.
     */
    public int getY() {
        return this.y;
    }
}