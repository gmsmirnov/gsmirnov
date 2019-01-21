package ru.job4j.bomberman;

/**
 * The game constants.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 09/10/2018
 */
public final class Constants {
    /**
     * The constant for awaiting next hero step.
     */
    public static final int TURN_TIMEOUT = 1000;

    /**
     * The constant for hero location timeout.
     */
    public static final int LOCATION_TIMEOUT = 100;

    /**
     * The constant for trying to lock a destination cell.
     */
    public static final int TRY_LOCK_TIMEOUT = 500;

    /**
     * The game board length.
     */
    public static final int BOARD_HEIGHT = 5;

    /**
     * The game board width.
     */
    public static final int BOARD_WIDTH = 5;

    /**
     * The maximum quantity of available directions to move character.
     */
    public static final int MAX_DIRECTIONS = 10;

    /**
     * The number of blocked cells on the game board.
     */
    public static final int NUMBER_OF_BLOCKS = (int) (Constants.BOARD_HEIGHT * Constants.BOARD_WIDTH * 0.2);

    /**
     * The number of blocked monsters on the game board.
     */
    public static final int NUMBER_OF_MONSTERS = ((int) (Constants.BOARD_HEIGHT * Constants.BOARD_WIDTH * 0.1) < 1
            ? (int) (Constants.BOARD_HEIGHT * Constants.BOARD_WIDTH * 0.1) : 1);
}