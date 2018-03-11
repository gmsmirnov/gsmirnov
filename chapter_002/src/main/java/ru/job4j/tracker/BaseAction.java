package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Basic abstract class for user actions.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 27/02/2018
 */
public abstract class BaseAction implements UserAction {
    /**
     * Unique key.
     */
    private final int key;

    /**
     * The name of user action.
     */
    private final String name;

    /**
     * BaseAction constructor - the template for creating action.
     *
     * @param key - unique key.
     * @param name - the name of user action.
     */
    public BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Executing user action. Abstract, needs to override in child class.
     *
     * @param input - the input interface for getting users messages.
     * @param output - the output interface for feedback to user.
     * @param tracker - requests container.
     */
    public abstract void execute(Input input, Output output, Tracker tracker);

    /**
     * Define the unique key for each user action.
     *
     * @return key.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Describing user action.
     *
     * @return string with user action description.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }

    /**
     * Creating a single message with requests details to translating to user through output interface.
     *
     * @param item - the request.
     * @return - message.
     */
    protected String messageCreation(Item item) {
        return String.format("%s: %s | %s: %s | %s: %s | %s: %s",
                "Request id", item.getId(), "Name", item.getName(),
                "Description", item.getDesc(), "Creation date", new Date(item.getCreation()).toString());
    }

    /**
     * Creating a group of messages with requests details to translating to user through output interface.
     *
     * @param items - requests.
     * @return - messages.
     */
    protected ArrayList<String> massMessageCreation(ArrayList<Item> items) {
        ArrayList<String> messages = new ArrayList<String>();
        for (int i = 0; i < items.size(); i++) {
            messages.add(this.messageCreation(items.get(i)));
        }
        return messages;
    }
}