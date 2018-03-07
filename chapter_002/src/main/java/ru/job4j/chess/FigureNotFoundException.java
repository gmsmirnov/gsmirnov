package ru.job4j.chess;

/**
 * FigureNotFoundException.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/03/2018
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Thrown when there is not a figure in the cell.
     *
     * @param msg - the message.
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
