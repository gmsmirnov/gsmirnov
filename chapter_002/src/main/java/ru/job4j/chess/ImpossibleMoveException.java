package ru.job4j.chess;

/**
 * ImpossibleMoveException.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/03/2018
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Thrown when the destination point is unreachable.
     *
     * @param msg - the message.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
