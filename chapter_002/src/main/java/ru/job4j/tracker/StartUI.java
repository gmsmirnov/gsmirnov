package ru.job4j.tracker;

import java.util.Date;

/**
 * The entry point. Provides the work of application.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/02/2018
 */
public class StartUI {
    /**
     * Menu constant for adding new request.
     */
    private static final String ADD = "0";

    /**
     * Menu constant for showing all registered requests.
     */
    private static final String SHOW_ALL = "1";

    /**
     * Menu constant for editing the request.
     */
    private static final String EDIT = "2";

    /**
     * Menu constant for deleting the request.
     */
    private static final String DELETE = "3";

    /**
     * Menu constant for finding the request by Id.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Menu constant for finding the requests by Name.
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Menu constant for exit from application.
     */
    private static final String EXIT = "6";

    /**
     * Input from user.
     */
    private final Input input;

    /**
     * Output to user.
     */
    private final Output output;

    /**
     * Requests database.
     */
    private final Tracker tracker;

    /**
     * StartUI constructor.
     *
     * @param input - the input interface for getting users messages.
     * @param output - the output interface for feedback to user.
     * @param tracker - requests container.
     */
    public StartUI(Input input, Output output, Tracker tracker) {
        this.input = input;
        this.output = output;
        this.tracker = tracker;
    }

    /**
     * StartUI initialization. Main application cycle.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select menu point: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            } else {
                this.output.print("Wrong point. Please enter the correct number.");
            }
        }
    }

    /**
     * New request creating.
     */
    private void createItem() {
        this.output.print("========Adding a new request==========");
        String name = this.input.ask("Please enter the name of your request: ");
        String desc = this.input.ask("Please enter the description of your request: ");
        Item item = this.tracker.add(new Item(name, desc));
        this.output.print("Your request was registered successfully, its id is: " + item.getId());
    }

    /**
     * All requests displaying.
     */
    private void showAllItems() {
        this.output.print("========Showing all requests==========");
        Item[] items = this.tracker.findAll();
        String message = "There is no any requests.";
        if (items != null) {
            this.output.massPrint(this.massMessageCreation(items));
        } else {
            this.output.print(message);
        }
    }

    /**
     * The request editing.
     */
    private void editItem() {
        this.output.print("=========Editing the request==========");
        String id = this.input.ask("Please enter the id of your request: ");
        String name = this.input.ask("Please enter the name of your request: ");
        String desc = this.input.ask("Please enter the description of your request: ");
        this.tracker.replace(id, new Item(name, desc));
        this.output.print("Your request was successfully updated.");
    }

    /**
     * The request deleting.
     */
    private void deleteItem() {
        this.output.print("========Deleting the request==========");
        this.tracker.delete(this.input.ask("Please enter the id of your request: "));
        this.output.print("Your request was successfully deleted.");
    }

    /**
     * The request detail displaying (finding by id of request).
     */
    private void findItemById() {
        this.output.print("=======Finding the request by id======");
        String message = "Incorrect id, request not found.";
        Item item = this.tracker.findById(this.input.ask("Please enter the id of your request: "));
        if (item != null) {
            message = this.messageCreation(item);
        }
        this.output.print(message);
    }

    /**
     * The requests details displaying (finding by name of request).
     */
    private void findItemsByName() {
        this.output.print("=====Finding the requests by name=====");
        String message = "There is no requests with this name.";
        Item[] items = this.tracker.findByName(this.input.ask("Please enter the name of your request: "));
        if (items != null) {
            this.output.massPrint(this.massMessageCreation(items));
        } else {
            this.output.print(message);
        }
    }

    /**
     * Menu displaying.
     */
    private void showMenu() {
        this.output.print("\nEnter the menu point and press 'Enter' key:");
        this.output.print("0. Add new request.");
        this.output.print("1. Show all requests.");
        this.output.print("2. Edit request.");
        this.output.print("3. Delete request.");
        this.output.print("4. Find request by id.");
        this.output.print("5. Find requests by name.");
        this.output.print("6. Exit.");
    }

    /**
     * Creating a single message with requests details to translating to user through output interface.
     *
     * @param item - the request.
     * @return - message.
     */
    private String messageCreation(Item item) {
        StringBuilder message = new StringBuilder("Request id: ");
        message.append(item.getId());
        message.append(". Name: ");
        message.append(item.getName());
        message.append(". Description: ");
        message.append(item.getDesc());
        message.append(". Creation date: ");
        message.append(new Date(item.getCreation()).toString());
        return message.toString();
    }

    /**
     * Creating a group of messages with requests details to translating to user through output interface.
     *
     * @param items - requests.
     * @return - messages.
     */
    private String[] massMessageCreation(Item[] items) {
        String[] messages = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            messages[i] = this.messageCreation(items[i]);
        }
        return messages;
    }

    /**
     * Entry point. Create and initialize StartUI.
     *
     * @param args - standard main params.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new ConsoleOutput(), new Tracker()).init();
    }
}