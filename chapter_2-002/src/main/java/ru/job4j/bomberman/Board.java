package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The game board for "Bomberman". It is a Reentrant Lock two dimensional array with specified parameters.
 * Each busy cell us locked and every available cells are unlocked.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 09/10/2018
 */
public final class Board {
    /**
     * The game board.
     */
    private final ReentrantLock[][] board;

    /**
     * Creates the game board and initialize it.
     */
    public Board(int height, int width) {
        this.board = new ReentrantLock[height][width];
        for (int xCoord = 0; xCoord < height; xCoord++) {
            for (int yCoord = 0; yCoord < width; yCoord++) {
                this.board[xCoord][yCoord] = new ReentrantLock();
            }
        }
    }

    /**
     * Locates character on the game board.
     *
     * @param hero - the specified character (bomberman or monster).
     */
    public void locate(Hero hero) {
        this.board[hero.getX()][hero.getY()].lock();
        System.out.println(Thread.currentThread().getName() + " located:[" + hero.getX() + "][" + hero.getY() + "]");
    }

    /**
     * Checks if the specified coordinate located within this game board.
     *
     * @param xDest - x coordinate.
     * @param yDest - y coordinate.
     * @return true if the specified coordinate located within this game board.
     */
    public boolean checkLimits(int xDest, int yDest) {
        return xDest >= 0 && xDest < Constants.BOARD_LENGTH && yDest >= 0 && yDest < Constants.BOARD_WIDTH;
    }

    /**
     * Tries to move from source cell to destination cell.
     *
     * @return true if move was successful.
     */
    public boolean move(int xSource, int ySource, int xDest, int yDest) {
        boolean result = false;
        try {
            if (this.board[xDest][yDest].tryLock(Constants.TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " Board destination:[" + xDest + "][" + yDest + "] locked successfully.");
                result = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (this.board[xSource][ySource].isHeldByCurrentThread()) {
                this.board[xSource][ySource].unlock();
                System.out.println(Thread.currentThread().getName() + " Board source:[" + xSource + "][" + ySource + "] unlocked successfully.");
            }
        }
        return result;
    }

    /**
     * Checks the specified cell. Is it available or not.
     *
     * @param xCoord - the x coordinate.
     * @param yCoord - the y coordinate.
     * @return - tru if the specified cell is available.
     */
    public boolean isAvailable(int xCoord, int yCoord) {
        return !this.board[xCoord][yCoord].isLocked();
    }

    /**
     * Clears the specified cell (unlock it).
     *
     * @param xCoord - the x coordinate.
     * @param yCoord - the y coordinate.
     */
    public void clear(int xCoord, int yCoord) {
        this.board[xCoord][yCoord].unlock();
    }
}