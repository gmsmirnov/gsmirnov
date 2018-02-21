package ru.job4j.tracker;

import java.util.Date;

/**
 * MenuTracker class. The applications menu and main cycle.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 20/02/2018
 */
public class MenuTracker {
    /**
     * The instance of Input interface.
     */
    private Input input;

    /**
     * The instance of Output interface.
     */
    private Output output;

    /**
     * The instance of Tracker.
     */
    private Tracker tracker;

    /**
     * The quantity of menu items.
     */
    private final int actionsQuantity = 7;

    /**
     * The array of menu items.
     */
    private UserAction[] actions = new UserAction[actionsQuantity];

    /**
     * The application termination flag.
     */
    protected static boolean exit = false;


    /**
     * MenuTracker constructor.
     *
     * @param input - the instance of Input interface.
     * @param output - the instance of Output interface.
     * @param tracker - the instance of Tracker.
     */
    public MenuTracker(Input input, Output output, Tracker tracker) {
        this.input = input;
        this.output = output;
        this.tracker = tracker;
    }

    /**
     * Menu filling.
     */
    public void fillMenu() {
        this.actions[0] = new MenuTracker.AddItem();
        this.actions[1] = new MenuTracker.ShawAllItems();
        this.actions[2] = new MenuTracker.EditItem();
        this.actions[3] = new MenuTracker.DeleteItem();
        this.actions[4] = new MenuTracker.FindItemById();
        this.actions[5] = new MenuTracker.FindItemsByName();
        this.actions[6] = new MenuTracker.Exit();
    }

    /**
     * Executing of action of menu item.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.output, this.tracker);
    }

    /**
     * Menu displaying.
     */
    public void showMenu() {
        for (UserAction action : this.actions) {
            if (action != null) {
                this.output.print(action.info());
            }
        }
    }

    /**
     * Base class for menu item classes. Creates messages to translating to user through output interface.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 20/02/2018
     */
    private static class UserActionBase {
        /**
         * Creating a single message with requests details to translating to user through output interface.
         *
         * @param item - the request.
         * @return - message.
         */
        protected String messageCreation(Item item) {
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
        protected String[] massMessageCreation(Item[] items) {
            String[] messages = new String[items.length];
            for (int i = 0; i < items.length; i++) {
                messages[i] = this.messageCreation(items[i]);
            }
            return messages;
        }
    }

    /**
     * New request creating.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 20/02/2018
     */
    private static class AddItem extends UserActionBase implements UserAction {
        /**
         * Define the unique key for each user action.
         *
         * @return key.
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Executing user action.
         *
         * @param input - the input interface for getting users messages.
         * @param output - the output interface for feedback to user.
         * @param tracker - requests container.
         */
        @Override
        public void execute(Input input, Output output, Tracker tracker) {
            output.print("========Adding a new request==========");
            String name = input.ask("Please enter the name of your request: ");
            String desc = input.ask("Please enter the description of your request: ");
            Item item = tracker.add(new Item(name, desc));
            output.print("Your request was registered successfully, its id is: " + item.getId());
        }

        /**
         * Describing user action.
         *
         * @return string with user action description.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new request.");
        }
    }

    /**
     * All requests displaying.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 20/02/2018
     */
    private static class ShawAllItems extends UserActionBase implements UserAction {
        /**
         * Define the unique key for each user action.
         *
         * @return key.
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Executing user action.
         *
         * @param input - the input interface for getting users messages.
         * @param output - the output interface for feedback to user.
         * @param tracker - requests container.
         */
        @Override
        public void execute(Input input, Output output, Tracker tracker) {
            output.print("========Showing all requests==========");
            Item[] items = tracker.findAll();
            String message = "There is no any requests.";
            if (items != null) {
                output.massPrint(this.massMessageCreation(items));
            } else {
                output.print(message);
            }
        }

        /**
         * Describing user action.
         *
         * @return string with user action description.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all requests.");
        }
    }

    /**
     * The request editing.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 20/02/2018
     */
    private static class EditItem extends UserActionBase implements UserAction {
        /**
         * Define the unique key for each user action.
         *
         * @return key.
         */
        @Override
        public int key() {
            return 2;
        }

        /**
         * Executing user action.
         *
         * @param input - the input interface for getting users messages.
         * @param output - the output interface for feedback to user.
         * @param tracker - requests container.
         */
        @Override
        public void execute(Input input, Output output, Tracker tracker) {
            output.print("=========Editing the request==========");
            String id = input.ask("Please enter the id of your request: ");
            String name = input.ask("Please enter the name of your request: ");
            String desc = input.ask("Please enter the description of your request: ");
            if (tracker.replace(id, new Item(name, desc))) {
                output.print("Your request was successfully updated.");
            } else {
                output.print("There is no request with such id.");
            }
        }

        /**
         * Describing user action.
         *
         * @return string with user action description.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit request.");
        }
    }

    /**
     * The request deleting.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 20/02/2018
     */
    private static class DeleteItem extends UserActionBase implements UserAction {
        /**
         * Define the unique key for each user action.
         *
         * @return key.
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Executing user action.
         *
         * @param input - the input interface for getting users messages.
         * @param output - the output interface for feedback to user.
         * @param tracker - requests container.
         */
        @Override
        public void execute(Input input, Output output, Tracker tracker) {
            output.print("========Deleting the request==========");
            if (tracker.delete(input.ask("Please enter the id of your request: "))) {
                output.print("Your request was successfully deleted.");
            } else {
                output.print("There is no request with such id.");
            }
        }

        /**
         * Describing user action.
         *
         * @return string with user action description.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete request.");
        }
    }

    /**
     * The request detail displaying (finding by id of request).
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 20/02/2018
     */
    private static class FindItemById extends UserActionBase implements UserAction {
        /**
         * Define the unique key for each user action.
         *
         * @return key.
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Executing user action.
         *
         * @param input - the input interface for getting users messages.
         * @param output - the output interface for feedback to user.
         * @param tracker - requests container.
         */
        @Override
        public void execute(Input input, Output output, Tracker tracker) {
            output.print("=======Finding the request by id======");
            String message = "Incorrect id, request not found.";
            Item item = tracker.findById(input.ask("Please enter the id of your request: "));
            if (item != null) {
                message = this.messageCreation(item);
            }
            output.print(message);
        }

        /**
         * Describing user action.
         *
         * @return string with user action description.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find request by id.");
        }
    }

    /**
     * The requests details displaying (finding by name of request).
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 20/02/2018
     */
    private static class FindItemsByName extends UserActionBase implements UserAction {
        /**
         * Define the unique key for each user action.
         *
         * @return key.
         */
        @Override
        public int key() {
            return 5;
        }

        /**
         * Executing user action.
         *
         * @param input - the input interface for getting users messages.
         * @param output - the output interface for feedback to user.
         * @param tracker - requests container.
         */
        @Override
        public void execute(Input input, Output output, Tracker tracker) {
            output.print("=====Finding the requests by name=====");
            String message = "There is no requests with this name.";
            Item[] items = tracker.findByName(input.ask("Please enter the name of your request: "));
            if (items != null) {
                output.massPrint(this.massMessageCreation(items));
            } else {
                output.print(message);
            }
        }

        /**
         * Describing user action.
         *
         * @return string with user action description.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find requests by name.");
        }
    }

    private static class Exit extends UserActionBase implements UserAction {
        /**
         * Define the unique key for each user action.
         *
         * @return key.
         */
        @Override
        public int key() {
            return 6;
        }

        /**
         * Executing user action.
         *
         * @param input - the input interface for getting users messages.
         * @param output - the output interface for feedback to user.
         * @param tracker - requests container.
         */
        @Override
        public void execute(Input input, Output output, Tracker tracker) {
            output.print("=======Application termination!=======");
            MenuTracker.exit = true;
        }

        /**
         * Describing user action.
         *
         * @return string with user action description.
         */
        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit.");
        }
    }
}