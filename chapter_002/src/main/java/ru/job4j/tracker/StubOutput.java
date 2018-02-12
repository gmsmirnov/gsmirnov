package ru.job4j.tracker;

/**
 * Stub class for output interface. Instead of translating message to user through interface, doing nothing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 12/02/2018
 */
public class StubOutput implements Output {
    /**
     * Doesn't translate a single message to user.
     *
     * @param message - translating message.
     */
    @Override
    public void print(String message) {
    }

    /**
     * Doesn't translate a group of messages to user.
     *
     * @param messages - a group of translating messages.
     */
    @Override
    public void massPrint(String[] messages) {
    }
}