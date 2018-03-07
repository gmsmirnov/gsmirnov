package ru.job4j.chess;

/**
 * Chessboard cell.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/03/2018
 */
public class Cell {
    private int xCoord;

    private int yCoord;

    public Cell(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            result = false;
        } else {
            Cell cell = (Cell) o;
            if (this.xCoord == cell.xCoord && this.yCoord == cell.yCoord) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}