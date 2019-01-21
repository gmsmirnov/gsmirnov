package ru.job4j.nonblock;

/**
 * The OptimisticException, which throws when current model's version in cache is equals or lower.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/08/2018
 */
public class OptimisticException extends RuntimeException {
    /**
     * Creates new exception with the specified message.
     *
     * @param message - the specified message.
     */
    public OptimisticException(String message) {
        super(message);
    }
}