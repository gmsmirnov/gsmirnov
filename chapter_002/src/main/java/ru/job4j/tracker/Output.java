package ru.job4j.tracker;

/**
 * The interface for feedback to user.
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
    void massPrint(String[] messages);
}