package ru.job4j.bomberman;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * The test for game board "Bomberman".
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 09/10/2018
 */
public class BoardTest {
    @Test
    public void whenCreateNewBoardThanAllCellsAreAvailable() {
        int height = 10;
        int width = 20;
        Board board = new Board(height, width, 0, 0);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                assertThat(board.isAvailable(x, y), is(true));
            }
        }
    }

    @Test
    public void whenLocateHeroThenLocatedCellIsUnavailable() {
        int height = 10;
        int width = 20;
        Board board = new Board(height, width, 0, 0);
        Hero bomberman = new Hero(5, 6);
        board.locateHero(bomberman);
        assertThat(board.isAvailable(bomberman.getX(), bomberman.getY()), is(false));
    }

    @Test
    public void whenBombermanMovesThenSourceBecomesAvailableAndDestinationBecomesUnavailable() throws InterruptedException {
        Board board = new Board(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, 0, 0); // 5*5
        Hero bomberman = new Hero(0, 0);
        board.locateHero(bomberman);
        assertThat(board.move(0, 0, 0, 1), is(true));
        Thread.sleep(100);
        assertThat(board.isAvailable(0, 0), is(true));
        assertThat(board.isAvailable(0, 1), is(false));
        assertThat(board.move(0, 1, 1, 1), is(true));
        Thread.sleep(100);
        assertThat(board.isAvailable(0, 1), is(true));
        assertThat(board.isAvailable(1, 1), is(false));
        assertThat(board.move(1, 1, 1, 2), is(true));
        Thread.sleep(100);
        assertThat(board.isAvailable(1, 1), is(true));
        assertThat(board.isAvailable(1, 2), is(false));
        assertThat(board.move(1, 2, 2, 2), is(true));
        Thread.sleep(100);
        assertThat(board.isAvailable(1, 2), is(true));
        assertThat(board.isAvailable(2, 2), is(false));
    }

    @Test
    public void whenBombermanInterruptedThenAllCellsAreAvailable() throws InterruptedException {
        int height = 10;
        int width = 20;
        Board board = new Board(height, width, 0, 0);
        Thread hero = new Thread(new HeroAction(board));
        hero.setName("Bomberman");
        hero.start();
        Thread.sleep(2000);
        hero.interrupt();
        hero.join();
        assertThat(hero.isAlive(), is(false));
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                assertThat(board.isAvailable(x, y), is(true));
            }
        }
    }

    @Test
    public void whenBoardHasBlocksThenTrue() {
        Board board = new Board(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, 0, 0); // 5*5
        board.locateBlock(0, 0);
        board.locateBlock(0, 1);
        board.locateBlock(1, 1);
        board.locateBlock(1, 2);
        board.locateBlock(2, 2);
        assertThat(board.isAvailable(0, 0), is(false));
        assertThat(board.isAvailable(0, 1), is(false));
        assertThat(board.isAvailable(1, 1), is(false));
        assertThat(board.isAvailable(1, 2), is(false));
        assertThat(board.isAvailable(2, 2), is(false));
        board.releaseBlocks();
    }

    @Test
    public void whenBoarReleasesBlocksThenTrue() {
        Board board = new Board(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH, 0, 0); // 5*5
        board.locateBlock(0, 0);
        board.locateBlock(0, 1);
        board.locateBlock(1, 1);
        board.locateBlock(1, 2);
        board.locateBlock(2, 2);
        board.releaseBlocks();
        for (int x = 0; x < Constants.BOARD_HEIGHT; x++) {
            for (int y = 0; y < Constants.BOARD_WIDTH; y++) {
                assertThat(board.isAvailable(x, y), is(true));
            }
        }
    }
}