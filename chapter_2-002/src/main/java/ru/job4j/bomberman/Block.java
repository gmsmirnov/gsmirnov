package ru.job4j.bomberman;

/**
 * Block description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/11/2018
 */
public final class Block extends GameObject {
    /**
     * The constructor which creates this block.
     *
     * @param xCoord - coordinate of this block.
     * @param yCoord - coordinate of this block.
     */
    public Block(int xCoord, int yCoord) {
        super(xCoord, yCoord);
    }
}