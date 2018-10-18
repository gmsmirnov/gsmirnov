package ru.job4j.bomberman;

/**
 * The character description. Defines the X and Y coordinates of a character (bomberman or monster).
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/10/2018
 */
public final class Hero {
    /**
     * The character's X coordinate.
     */
    private final int x;

    /**
     * The character's Y coordinate.
     */
    private final int y;

    /**
     * Creates new character with the specified coordinates.
     *
     * @param x - the specified X coordinate.
     * @param y - the specified Y coordinate.
     */
    public Hero(int x, int y) {
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