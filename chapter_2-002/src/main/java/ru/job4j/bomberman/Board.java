package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The game board for "Bomberman". It is a Reentrant Lock two dimensional array with specified parameters.
 * Each busy cell us locked and every available cells are unlocked.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/10/2018
 */
public final class Board {
    /**
     * The game board.
     */
    private static ReentrantLock[][] board;

    /**
     * Initialize the game board.
     */
    public static void init() {
        Board.board = new ReentrantLock[Constants.BOARD_LENGTH][Constants.BOARD_WIDTH];
        for (int xCoord = 0; xCoord < Constants.BOARD_LENGTH; xCoord++) {
            for (int yCoord = 0; yCoord < Constants.BOARD_WIDTH; yCoord++) {
                Board.board[xCoord][yCoord] = new ReentrantLock();
            }
        }
    }

    /**
     * Locates character on the game board.
     *
     * @param hero - the specified character (bomberman or monster).
     */
    public static void locate(Hero hero) {
        Board.board[hero.getX()][hero.getY()].lock();
        System.out.println(Thread.currentThread().getName() + " located:[" + hero.getX() + "][" + hero.getY() + "]");
    }

    /**
     * Checks if the specified coordinate located within this game board.
     *
     * @param xDest - x coordinate.
     * @param yDest - y coordinate.
     * @return true if the specified coordinate located within this game board.
     */
    public static boolean checkLimits(int xDest, int yDest) {
        return xDest >= 0 && xDest < Constants.BOARD_LENGTH && yDest >= 0 && yDest < Constants.BOARD_WIDTH;
    }

    /**
     * Tries to move from source cell to destination cell.
     *
     * @return true if move was successful.
     */
    public static boolean move(int xSource, int ySource, int xDest, int yDest) {
        boolean result = false;
        try {
            if (Board.board[xDest][yDest].tryLock(Constants.TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " Board destination:[" + xDest + "][" + yDest + "] locked successfully.");
                result = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (Board.board[xSource][ySource].isHeldByCurrentThread()) {
                Board.board[xSource][ySource].unlock();
                System.out.println(Thread.currentThread().getName() + " Board source:[" + xSource + "][" + ySource + "] unlocked successfully.");
            }
        }
        return result;
    }
}