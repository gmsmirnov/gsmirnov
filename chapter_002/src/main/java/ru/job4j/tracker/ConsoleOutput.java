package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Provides applications console output for user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 10/02/2018
 */
public class ConsoleOutput implements Output {
    /**
     * Translates a single message to user.
     *
     * @param message - translating message.
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Translates a group of messages to user.
     *
     * @param messages - a group of translating messages.
     */
    @Override
    public void massPrint(ArrayList<String> messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }
}