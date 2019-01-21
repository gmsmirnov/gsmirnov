package ru.job4j.tracker;

/**
 * Interface for user actions.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 20/02/2018
 */
interface UserAction {
    /**
     * Define the unique key for each user action.
     *
     * @return key.
     */
    int key();

    /**
     * Executing user action.
     *
     * @param input - the input interface for getting users messages.
     * @param output - the output interface for feedback to user.
     * @param tracker - requests container.
     */
    void execute(Input input, Output output, ITracker tracker);

    /**
     * Describing user action.
     *
     * @return string with user action description.
     */
    String info();
}