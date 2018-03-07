package ru.job4j.chess;

/**
 * OccupiedWayException.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/03/2018
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Thrown when the way is occupied.
     *
     * @param msg - the message.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
