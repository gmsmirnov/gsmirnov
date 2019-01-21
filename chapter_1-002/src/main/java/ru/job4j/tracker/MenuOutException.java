package ru.job4j.tracker;

/**
 * Generate exception, which appears when user choose not existing menu point.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/02/2018
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String msg) {
        super(msg);
    }
}