package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * MenuTracker class. The applications menu.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.5
 * @since 20/02/2018
 */
public class MenuTracker {
    /**
     * The instance of Input interface.
     */
    private final Input input;

    /**
     * The instance of Output interface.
     */
    private final Output output;

    /**
     * The instance of Tracker.
     */
    private final Tracker tracker;

    /**
     * The array of menu items.
     */
    private final ArrayList<UserAction> actions = new ArrayList<UserAction>();


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
    public void fillMenu(StartUI ui) {
        this.actions.add(this.new AddItem(0, "Add new request."));
        this.actions.add(new MenuTracker.ShawAllItems(1, "Show all requests."));
        this.actions.add(new MenuTracker.EditItem(2, "Edit request."));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete request."));
        this.actions.add(new MenuTracker.FindItemById(4, "Find request by id."));
        this.actions.add(new MenuTracker.FindItemsByName(5, "Find requests by name."));
        this.actions.add(new Exit(6, "Exit.", ui));
    }

    /**
     * Executing of action of menu item.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.output, this.tracker);
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
     * Gets menu points range.
     *
     * @return - the range.
     */
    public ArrayList<Integer> getMenuPointsRange() {
        ArrayList<Integer> range = new ArrayList<Integer>();
        for (int i = 0; i < this.actions.size(); i++) {
            range.add(this.actions.get(i).key());
        }
        return range;
    }

    /**
     * New request creating. Non-static class.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.2
     * @since 20/02/2018
     */
    private class AddItem extends BaseAction {
        /**
         * AddItem constructor.
         *
         * @param key - unique key.
         * @param name - the name of user action.
         */
        public AddItem(final int key, final String name) {
            super(key, name);
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
    }

    /**
     * All requests displaying.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.1
     * @since 20/02/2018
     */
    private static class ShawAllItems extends BaseAction {
        /**
         * ShawAllItems constructor.
         *
         * @param key - unique key.
         * @param name - the name of user action.
         */
        public ShawAllItems(final int key, final String name) {
            super(key, name);
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
            ArrayList<Item> items = tracker.findAll();
            String message = "There is no any requests.";
            if (items != null) {
                output.massPrint(this.massMessageCreation(items));
            } else {
                output.print(message);
            }
        }
    }

    /**
     * The request editing.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.1
     * @since 20/02/2018
     */
    private static class EditItem extends BaseAction {
        /**
         * EditItem constructor.
         *
         * @param key - unique key.
         * @param name - the name of user action.
         */
        public EditItem(final int key, final String name) {
            super(key, name);
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
    }

    /**
     * The request deleting.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.1
     * @since 20/02/2018
     */
    private static class DeleteItem extends BaseAction {
        /**
         * DeleteItem constructor.
         *
         * @param key - unique key.
         * @param name - the name of user action.
         */
        public DeleteItem(final int key, final String name) {
            super(key, name);
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
    }

    /**
     * The request detail displaying (finding by id of request).
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.1
     * @since 20/02/2018
     */
    private static class FindItemById extends BaseAction {
        /**
         * FindItemById constructor.
         *
         * @param key - unique key.
         * @param name - the name of user action.
         */
        public FindItemById(final int key, final String name) {
            super(key, name);
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
    }

    /**
     * The requests details displaying (finding by name of request).
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.1
     * @since 20/02/2018
     */
    private static class FindItemsByName extends BaseAction {
        /**
         * FindItemsByName constructor.
         *
         * @param key - unique key.
         * @param name - the name of user action.
         */
        public FindItemsByName(final int key, final String name) {
            super(key, name);
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
            ArrayList<Item> items = tracker.findByName(input.ask("Please enter the name of your request: "));
            if ((items != null) && (items.size() != 0)) {
                output.massPrint(this.massMessageCreation(items));
            } else {
                output.print(message);
            }
        }
    }
}

/**
 * Application termination. Outer class in the same file.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 20/02/2018
 */
class Exit extends BaseAction {
    /**
     * The StartUI instance.
     */
    private final StartUI ui;

    /**
     * Exit constructor.
     *
     * @param key - unique key.
     * @param name - the name of user action.
     * @param ui - the entry point instance.
     */
    public Exit(final int key, final String name, StartUI ui) {
        super(key, name);
        this.ui = ui;
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
        this.ui.stop();
    }
}