package ru.job4j.tracker;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * The entry point. Provides the work of application. Main cycle.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.3
 * @since 10/02/2018
 */
public class StartUI {
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
     * Logger for massages and exceptions.
     */
    final static Logger LOGGER = LogManager.getLogger(StartUI.class);

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
        MenuTracker menu = new MenuTracker(this.input, this.output, this.tracker);
        menu.fillMenu();
        int[] range = menu.getMenuPointsRange();
        while (!MenuTracker.exit) {
            this.output.print("\nEnter the menu point and press 'Enter' key:");
            menu.showMenu();
            menu.select(this.input.ask("Select menu point: ", range));
        }
    }

    /**
     * Entry point. Create and initialize StartUI.
     *
     * @param args - standard main params.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new ConsoleOutput(), new Tracker()).init();
    }
}