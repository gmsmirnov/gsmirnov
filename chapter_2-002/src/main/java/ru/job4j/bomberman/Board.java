package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The game board for "Bomberman". It is a Reentrant Lock two dimensional array with specified parameters.
 * Each busy cell us locked and every available cells are unlocked.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 09/10/2018
 */
public final class Board {
    /**
     * The game board.
     */
    private final ReentrantLock[][] board;

    /**
     * The quantity of blocked cells.
     */
    private final int blocksNum;

    /**
     * The quantity of monsters located on the game board.
     */
    private final int monstersNum;

    /**
     * The height of the game board.
     */
    private final int height;

    /**
     * The width of the game board.
     */
    private final int width;

    /**
     * The blocks coordinates.
     */
    private final ArrayList<Block> blocks;

    /**
     * Creates the game board and initialize it.
     *
     * @param height - the height of the game board.
     * @param width - the width of the game board.
     * @param blocksNum - the number of blocks on the game board.
     * @param monstersNum - the number of monsters on the game board.
     */
    public Board(int height, int width, int blocksNum, int monstersNum) {
        this.height = height;
        this.width = width;
        this.blocksNum = this.checkBlocksNum(blocksNum);
        this.monstersNum = this.monstersCheck(monstersNum);
        this.blocks = new ArrayList<Block>(this.blocksNum);
        this.board = new ReentrantLock[height][width];
        for (int xCoord = 0; xCoord < this.height; xCoord++) {
            for (int yCoord = 0; yCoord < this.width; yCoord++) {
                this.board[xCoord][yCoord] = new ReentrantLock();
            }
        }
        this.locateBlocks();
    }

    /**
     * Locates character on the game board.
     *
     * @param hero - the specified character (bomberman or monster).
     * @return true if location successful.
     */
    public boolean locateHero(Hero hero) {
        boolean result = false;
        try {
            this.board[hero.getX()][hero.getY()].tryLock(Constants.TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
            result = true;
            System.out.println(Thread.currentThread().getName() + " located:[" + hero.getX() + "][" + hero.getY() + "]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Locates the monsters on the board.
     */
    public void locateMonsters() {
        int counter = 0;
        while (counter < this.monstersNum) {
            if (this.locateHero(new Hero((int) (Math.random() * this.height), (int) (Math.random() * this.width)))) {
                counter++;
            }
        }
    }

    /**
     * Tries to locateHero the block on the specified coordinates. If cell is not blocked until timeout, then the result
     * is false.
     *
     * @param xCoord - the specified X coordinate.
     * @param yCoord - the specified Y coordinate.
     * @return true if cell blocked successful.
     */
    public boolean locateBlock(int xCoord, int yCoord) {
        boolean result = false;
        try {
            this.board[xCoord][yCoord].tryLock(Constants.TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
            this.blocks.add(new Block(xCoord, yCoord));
            System.out.println(Thread.currentThread().getName() + " located block:[" + xCoord + "][" + yCoord + "]");
            result = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Locates the blocks on the board.
     */
    public void locateBlocks() {
        int counter = 0;
        while (counter < this.blocksNum) {
            if (this.locateBlock((int) (Math.random() * this.height), (int) (Math.random() * this.width))) {
                counter++;
            }
        }
    }

    /**
     * Checks the quantity of blocks. If it is lower than '0' then it sets to '0', if it is greater than 50%
     * of game board cells then the quantity sets to 50% of game board cells.
     *
     * @param blocksNum - the specified number of blocks to check.
     * @return the quantity of blocked cells.
     */
    private int checkBlocksNum(int blocksNum) {
        int result = blocksNum;
        if (blocksNum < 0) {
            result = 0;
        } else if (blocksNum > (int) (this.height * this.width * 0.5)) {
            result = (int) (Constants.BOARD_HEIGHT * Constants.BOARD_HEIGHT * 0.5);
        }
        return result;
    }

    /**
     * Checks the quantity of monsters. If it is lower than '0' then it sets to '0', if it is
     * greater than 20% of empty game board cells then the quantity sets to 20% of empty game board cells.
     *
     * @param monstersNum - the specified number of monsters.
     * @return the quantity of monsters.
     */
    private int monstersCheck(int monstersNum) {
        int result = monstersNum;
        if (monstersNum < 0) {
            result = 0;
        } else if (monstersNum > (int) ((this.height * this.width - this.blocksNum - 1) * 0.2)) {
            result = (int) ((this.height * this.width - this.blocksNum - 1) * 0.2);
        }
        return result;
    }

    /**
     * Checks if the specified coordinate located within this game board.
     *
     * @param xDest - x coordinate.
     * @param yDest - y coordinate.
     * @return true if the specified coordinate located within this game board.
     */
    public boolean checkLimits(int xDest, int yDest) {
        return xDest >= 0 && xDest < this.height && yDest >= 0 && yDest < this.width;
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
        System.out.println(Thread.currentThread().getName() + " released:[" + xCoord + "][" + yCoord + "]");
    }

    /**
     * Releases blocks of the game board.
     */
    public void releaseBlocks() {
        for (Block block : this.blocks) {
            this.clear(block.getX(), block.getY());
        }
    }
}