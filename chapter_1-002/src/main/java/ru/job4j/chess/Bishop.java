package ru.job4j.chess;

import java.util.Arrays;

/**
 * Bishop figure.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/03/2018
 */
public class Bishop extends Figure {
    /**
     * Max quantity of steps that bishop can do.
     */
    private final int maxSteps = 7;

    /**
     * Bishop constructor
     *
     * @param board - the chessboard which contains this bishop.
     * @param position - the bishops position on the board.
     */
    public Bishop(Board board, Cell position) {
        super(board, position);
    }

    /**
     * Calculates possible way that bishop does from source cell to destination cell.
     *
     * @param board - the chessboard which contains this bishop.
     * @param source - the source cell.
     * @param dest - the destination cell.
     * @return - possible way.
     * @throws ImpossibleMoveException - when destination cell is unreachable for bishop.
     */
    @Override
    public Cell[] way(Board board, Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] path = null;
        if (source.getXCoord() == dest.getXCoord() || source.getYCoord() == dest.getYCoord()) {
            throw new ImpossibleMoveException("Destination cell is unreachable.");
        }
        if (source.getXCoord() < dest.getXCoord() && source.getYCoord() < dest.getYCoord()) {
            path = this.moveToUpperRightConner(board, source, dest);
        } else if (source.getXCoord() < dest.getXCoord() && source.getYCoord() > dest.getYCoord()) {
            path = this.moveToLowerRightConner(board, source, dest);
        } else if (source.getXCoord() > dest.getXCoord() && source.getYCoord() > dest.getYCoord()) {
            path = this.moveToLowerLeftConner(board, source, dest);
        } else if (source.getXCoord() > dest.getXCoord() && source.getYCoord() < dest.getYCoord()) {
            path = this.moveToUpperLeftConner(board, source, dest);
        }
        if (path == null || path.length == 0) {
            throw new ImpossibleMoveException("Destination cell is unreachable.");
        }
        return path;
    }

    /**
     * Creates new bishop after successful move.
     *
     * @param board - the chessboard which contains this bishop.
     * @param dest - new bishop position.
     * @return - new bishop in new location.
     */
    @Override
    public Figure copy(Board board, Cell dest) {
        return new Bishop(board, dest);
    }

    /**
     * Calculates possible way that bishop does from source cell to destination cell when destination cell is righter
     * and upper then source cell.
     *
     * @param board - the chessboard which contains this bishop.
     * @param source - the source cell.
     * @param dest - the destination cell.
     * @return - possible way.
     */
    private Cell[] moveToUpperRightConner(Board board, Cell source, Cell dest) {
        Cell[] path = new Cell[this.maxSteps];
        int pathPosition = 0;
        int step = 0;
        int xCoord = source.getXCoord() + 1;
        int yCoord = source.getYCoord() + 1;
        while (xCoord <= board.getRightBoard() && yCoord <= board.getUpperBoard() && step < this.maxSteps) {
            path[step] = new Cell(xCoord++, yCoord++);
            if (path[step].equals(dest)) {
                pathPosition = ++step;
                break;
            }
            step++;
        }
        return Arrays.copyOf(path, pathPosition);
    }

    /**
     * Calculates possible way that bishop does from source cell to destination cell when destination cell is righter
     * and lower then source cell.
     *
     * @param board - the chessboard which contains this bishop.
     * @param source - the source cell.
     * @param dest - the destination cell.
     * @return - possible way.
     */
    private Cell[] moveToLowerRightConner(Board board, Cell source, Cell dest) {
        Cell[] path = new Cell[this.maxSteps];
        int pathPosition = 0;
        int step = 0;
        int xCoord = source.getXCoord() + 1;
        int yCoord = source.getYCoord() - 1;
        while (xCoord <= board.getRightBoard() && yCoord >= board.getLowerBoard() && step <= this.maxSteps) {
            path[step] = new Cell(xCoord++, yCoord--);
            if (path[step].equals(dest)) {
                pathPosition = ++step;
                break;
            }
            step++;
        }
        return Arrays.copyOf(path, pathPosition);
    }

    /**
     * Calculates possible way that bishop does from source cell to destination cell when destination cell is lefter
     * and lower then source cell.
     *
     * @param board - the chessboard which contains this bishop.
     * @param source - the source cell.
     * @param dest - the destination cell.
     * @return - possible way.
     */
    private Cell[] moveToLowerLeftConner(Board board, Cell source, Cell dest) {
        Cell[] path = new Cell[this.maxSteps];
        int pathPosition = 0;
        int step = 0;
        int xCoord = source.getXCoord() - 1;
        int yCoord = source.getYCoord() - 1;
        while (xCoord >= board.getLeftBoard() && yCoord >= board.getLowerBoard() && step <= this.maxSteps) {
            path[step] = new Cell(xCoord--, yCoord--);
            if (path[step].equals(dest)) {
                pathPosition = ++step;
                break;
            }
            step++;
        }
        return Arrays.copyOf(path, pathPosition);
    }

    /**
     * Calculates possible way that bishop does from source cell to destination cell when destination cell is lefter
     * and upper then source cell.
     *
     * @param board - the chessboard which contains this bishop.
     * @param source - the source cell.
     * @param dest - the destination cell.
     * @return - possible way.
     */
    private Cell[] moveToUpperLeftConner(Board board, Cell source, Cell dest) {
        Cell[] path = new Cell[this.maxSteps];
        int pathPosition = 0;
        int step = 0;
        int xCoord = source.getXCoord() - 1;
        int yCoord = source.getYCoord() + 1;
        while (xCoord >= board.getLeftBoard() && yCoord <= board.getUpperBoard() && step <= this.maxSteps) {
            path[step] = new Cell(xCoord--, yCoord++);
            if (path[step].equals(dest)) {
                pathPosition = ++step;
                break;
            }
            step++;
        }
        return Arrays.copyOf(path, pathPosition);
    }
}