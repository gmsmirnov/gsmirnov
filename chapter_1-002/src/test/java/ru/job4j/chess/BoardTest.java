package ru.job4j.chess;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Board test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 03/03/2018
 */
public class BoardTest {
    private final Board board = new Board();

    @Before
    public void createBoard() {
        this.board.add(new Bishop(this.board, new Cell(0, 7)));
        this.board.add(new Bishop(this.board, new Cell(2, 7)));
        this.board.add(new Bishop(this.board, new Cell(5, 7)));
        this.board.add(new Bishop(this.board, new Cell(1, 6)));
        this.board.add(new Bishop(this.board, new Cell(3, 6)));
        this.board.add(new Bishop(this.board, new Cell(4, 6)));
        this.board.add(new Bishop(this.board, new Cell(6, 6)));
        this.board.add(new Bishop(this.board, new Cell(1, 5)));
        this.board.add(new Bishop(this.board, new Cell(2, 5)));
        this.board.add(new Bishop(this.board, new Cell(4, 5)));
        this.board.add(new Bishop(this.board, new Cell(6, 5)));
        this.board.add(new Bishop(this.board, new Cell(7, 5)));
        this.board.add(new Bishop(this.board, new Cell(0, 4)));
        this.board.add(new Bishop(this.board, new Cell(3, 3)));
        this.board.add(new Bishop(this.board, new Cell(4, 3)));
        this.board.add(new Bishop(this.board, new Cell(1, 2)));
        this.board.add(new Bishop(this.board, new Cell(4, 2)));
        this.board.add(new Bishop(this.board, new Cell(2, 1)));
        this.board.add(new Bishop(this.board, new Cell(4, 1)));
        this.board.add(new Bishop(this.board, new Cell(5, 1)));
        this.board.add(new Bishop(this.board, new Cell(6, 1)));
        this.board.add(new Bishop(this.board, new Cell(7, 1)));
        this.board.add(new Bishop(this.board, new Cell(2, 0)));
        this.board.add(new Bishop(this.board, new Cell(3, 0)));
        this.board.add(new Bishop(this.board, new Cell(6, 0)));
    }

    @Test
    public void whenMoveIsAvailable() {
        boolean result = this.board.move(new Cell(3, 3), new Cell(5, 5));
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMoveIsAvailable2() {
        boolean result = this.board.move(new Cell(4, 3), new Cell(5, 2));
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMoveIsAvailable3() {
        boolean result = this.board.move(new Cell(4, 3), new Cell(3, 2));
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMoveIsAvailable4() {
        boolean result = this.board.move(new Cell(4, 3), new Cell(3, 4));
        boolean expected = true;
        assertThat(result, is(expected));
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWayIsOccupied() {
        this.board.move(new Cell(3, 3), new Cell(6, 6));
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWayIsOccupied2() {
        this.board.move(new Cell(3, 3), new Cell(0, 6));
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWayIsOccupied3() {
        this.board.move(new Cell(4, 3), new Cell(1, 0));
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWayIsOccupied4() {
        this.board.move(new Cell(4, 3), new Cell(0, 7));
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenFigureNotFound() {
        this.board.move(new Cell(2, 2), new Cell(6, 6));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenDestinationPointIsUnreachable() {
        this.board.move(new Cell(1, 5), new Cell(5, 4));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenDestinationPointIsUnreachable2() {
        this.board.move(new Cell(3, 3), new Cell(7, 0));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenDestinationPointIsUnreachable3() {
        this.board.move(new Cell(3, 3), new Cell(1, 0));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenDestinationPointIsUnreachable4() {
        this.board.move(new Cell(3, 3), new Cell(0, 7));
    }
}