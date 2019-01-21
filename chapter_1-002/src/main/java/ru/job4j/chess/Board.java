package ru.job4j.chess;

/**
 * Chessboard.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/03/2018
 */
public class Board {
    /**
     * The upper boarder of the chessboard.
     */
    private final int upperBoard = 7;

    /**
     * The right boarder of the chessboard.
     */
    private final int rightBoard = 7;

    /**
     * The lower boarder of the chessboard.
     */
    private final int lowerBoard = 0;

    /**
     * The left boarder of the chessboard.
     */
    private final int leftBoard = 0;

    /**
     * The max figures quantity on the chessboard.
     */
    private final int maxFiguresQuantity = 32;

    /**
     * The figures on the chessboard.
     */
    private final Figure[] figures = new Figure[maxFiguresQuantity];

    /**
     * Gets upper boarder.
     *
     * @return - upper boarder of the chessboard.
     */
    public int getUpperBoard() {
        return this.upperBoard;
    }

    /**
     * Gets right boarder.
     *
     * @return - right boarder of the chessboard.
     */
    public int getRightBoard() {
        return this.rightBoard;
    }

    /**
     * Gets lower boarder.
     *
     * @return - lower boarder of the chessboard.
     */
    public int getLowerBoard() {
        return this.lowerBoard;
    }

    /**
     * Gets left boarder.
     *
     * @return - left boarder of the chessboard.
     */
    public int getLeftBoard() {
        return this.leftBoard;
    }

    /**
     * This method adds a new figure on the chessboard.
     *
     * @param figure - new figure.
     * @return true if addition complete.
     */
    public boolean add(Figure figure) {
        boolean result = false;
        for (int i = 0; i < this.figures.length; i++) {
            if (this.figures[i] == null) {
                this.figures[i] = figure;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Replace the figure with old position by figure with new position.
     *
     * @param oldFigure - the source cell.
     * @param newFigure - the destination cell.
     * @return - true, replace is complete.
     */
    private boolean replace(Figure oldFigure, Figure newFigure) {
        boolean result = false;
        for (int i = 0; i < this.figures.length; i++) {
            if (this.figures[i] != null && this.figures[i].equals(oldFigure)) {
                this.figures[i] = newFigure;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * If there is a figure in the source cell, try to move this figure into the destination cell.
     *
     * @param source - the source cell.
     * @param dest - the destination cell.
     * @return - true, if there is a figure and it can walk to the destination cell.
     * @throws FigureNotFoundException - the cell is empty.
     * @throws ImpossibleMoveException - the source cell is equal destination cell or is unreachable.
     * @throws OccupiedWayException - the way is occupied by another figure.
     */
    public boolean move(Cell source, Cell dest) throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        if (source.equals(dest)) {
            throw new ImpossibleMoveException("The source cell is equal destination cell!");
        }
        Figure figure = this.isFigure(source);
        if (this.isAvailable(figure.way(this, source, dest))) {
            this.replace(figure, figure.copy(this, dest));
        }
        return true;
    }

    /**
     * Check the figure in the cell.
     *
     * @param source - the source cell.
     * @return - true, if there is a figure in the cell.
     */
    private Figure isFigure(Cell source) {
        Figure result = null;
        for (Figure figure : this.figures) {
            if (figure != null && figure.getPosition().equals(source)) {
                result = figure;
                break;
            }
        }
        if (result == null) {
            throw new FigureNotFoundException("The source cell is empty!");
        }
        return result;
    }

    /**
     * Check the the path. Is available or not.
     *
     * @param path - the path.
     * @return - true, if the path is available.
     * @throws OccupiedWayException - the way is occupied by another figure.
     */
    private boolean isAvailable(Cell[] path) {
        for (Cell step : path) {
            for (Figure figure : this.figures) {
                if (figure != null && step.equals(figure.getPosition())) {
                    throw new OccupiedWayException("The way is occupied by another figure!");
                }
            }
        }
        return true;
    }
}