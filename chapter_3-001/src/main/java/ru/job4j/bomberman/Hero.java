package ru.job4j.bomberman;

/**
 * The character description. Defines the X and Y coordinates of a character (bomberman or monster).
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 09/10/2018
 */
public final class Hero extends GameObject {
    /**
     * Creates new character with the specified coordinates.
     *
     * @param x - the specified X coordinate.
     * @param y - the specified Y coordinate.
     */
    public Hero(int x, int y) {
        super(x, y);
    }
}