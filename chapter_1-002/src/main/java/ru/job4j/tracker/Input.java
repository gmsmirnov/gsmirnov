package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * The interface for getting users messages.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 10/02/2018
 */
interface Input {
    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @return - users answer.
     */
    String ask(String request);

    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @param range - the range of allowable points.
     * @return - the point that user was selected.
     */
    int ask(String request, ArrayList<Integer> range);
}