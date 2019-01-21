package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * The interface for feedback to user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 10/02/2018
 */
interface Output {
    /**
     * Translates a single message to user.
     *
     * @param message - translating message.
     */
    void print(String message);

    /**
     * Translates a group of messages to user.
     *
     * @param messages - a group of translating messages.
     */
    void massPrint(ArrayList<String> messages);
}