package ru.job4j.bomberman;

/**
 * The game constants.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/10/2018
 */
public final class Constants {
    /**
     * The constant for awaiting next hero step.
     */
    public static final int TURN_TIMEOUT = 1000;

    /**
     * The constant for trying to lock a destination cell.
     */
    public static final int TRY_LOCK_TIMEOUT = 500;

    /**
     * The game board length.
     */
    public static final int BOARD_LENGTH = 3;

    /**
     * The game board width.
     */
    public static final int BOARD_WIDTH = 3;

    /**
     * The maximum quantity of available directions to move character.
     */
    public static final int MAX_DIRECTIONS = 4;
}