package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Bishop test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 03/03/2018
 */
public class BishopTest {
    @Test
    public void whenDestinationCellIsAccessible() {
        Board board = new Board();
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(7, 7);
        Bishop bishop = new Bishop(board, source);
        Cell[] expected = new Cell[] {new Cell(1, 1), new Cell(2, 2),
                new Cell(3, 3), new Cell(4, 4),
                new Cell(5, 5), new Cell(6, 6),
                new Cell(7, 7)};
        Cell[] result = bishop.way(board, source, dest);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDestinationCellIsAccessible2() {
        Board board = new Board();
        Cell source = new Cell(7, 7);
        Cell dest = new Cell(0, 0);
        Bishop bishop = new Bishop(board, source);
        Cell[] expected = new Cell[] {new Cell(6, 6), new Cell(5, 5),
                new Cell(4, 4), new Cell(3, 3),
                new Cell(2, 2), new Cell(1, 1),
                new Cell(0, 0)};
        Cell[] result = bishop.way(board, source, dest);
        assertThat(result, is(expected));
    }
    @Test
    public void whenDestinationCellIsAccessible3() {
        Board board = new Board();
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(3, 3);
        Bishop bishop = new Bishop(board, source);
        Cell[] expected = new Cell[] {new Cell(1, 1), new Cell(2, 2),
                new Cell(3, 3)};
        Cell[] result = bishop.way(board, source, dest);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDestinationCellIsAccessible4() {
        Board board = new Board();
        Cell source = new Cell(2, 4);
        Cell dest = new Cell(5, 1);
        Bishop bishop = new Bishop(board, source);
        Cell[] expected = new Cell[] {new Cell(3, 3), new Cell(4, 2),
                new Cell(5, 1)};
        Cell[] result = bishop.way(board, source, dest);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDestinationCellIsAccessible5() {
        Board board = new Board();
        Cell source = new Cell(5, 1);
        Cell dest = new Cell(2, 4);
        Bishop bishop = new Bishop(board, source);
        Cell[] expected = new Cell[] {new Cell(4, 2), new Cell(3, 3),
                new Cell(2, 4)};
        Cell[] result = bishop.way(board, source, dest);
        assertThat(result, is(expected));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenDestCellIsUnreachable() {
        Board board = new Board();
        Cell source = new Cell(5, 5);
        Cell dest = new Cell(5, 4);
        Bishop bishop = new Bishop(board, source);
        bishop.way(board, source, dest);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenDestCellIsUnreachable2() {
        Board board = new Board();
        Cell source = new Cell(1, 5);
        Cell dest = new Cell(5, 4);
        Bishop bishop = new Bishop(board, source);
        bishop.way(board, source, dest);
    }
}