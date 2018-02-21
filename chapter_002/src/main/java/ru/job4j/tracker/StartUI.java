package ru.job4j.tracker;

/**
 * The entry point. Provides the work of application.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
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

        while (!menu.exit) {
            this.output.print("\nEnter the menu point and press 'Enter' key:");
            menu.showMenu();
            try {
                menu.select(Integer.valueOf(this.input.ask("Select menu point: ")));
            } catch (Exception e) {
                this.output.print("Wrong point. Please enter the correct number.");
            }
        }
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