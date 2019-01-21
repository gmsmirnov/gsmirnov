package ru.job4j.chess;

/**
 * Figure.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/03/2018
 */
public abstract class Figure {
    /**
     * The chessboard instance.
     */
    private final Board board;

    /**
     * Figure position on the chessboard.
     */
    private final Cell position;

    /**
     * Figure constructor.
     *
     * @param board - the chessboard which contains this figure.
     * @param position - the position of figure on the board.
     */
    public Figure(Board board, Cell position) {
        this.board = board;
        this.position = position;
    }

    /**
     * Calculates possible way that figure does from source cell to destination cell.
     *
     * @param board - the chessboard which contains this figure.
     * @param source - the source cell.
     * @param dest - the destination cell.
     * @return - possible way.
     * @throws ImpossibleMoveException - when destination cell is unreachable for this figure.
     */
    public abstract Cell[] way(Board board, Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Creates new figure after successful move.
     *
     * @param board - the chessboard which contains this figure.
     * @param dest - new figure position.
     * @return - new figure in new location.
     */
    public abstract Figure copy(Board board, Cell dest);

    /**
     * Get the position of figure.
     *
     * @return - the position.
     */
    public Cell getPosition() {
        return this.position;
    }
}