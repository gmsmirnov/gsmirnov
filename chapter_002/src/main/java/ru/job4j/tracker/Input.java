package ru.job4j.tracker;

/**
 * The interface for getting users messages.
 */
interface Input {
    /**
     * Asking users answer.
     *
     * @param request - a message to user.
     * @return - users answer.
     */
    String ask(String request);
}