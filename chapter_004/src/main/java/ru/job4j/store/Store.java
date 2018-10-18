package ru.job4j.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Checks differences between two lists. Counts quantity of added users in new list, modified users in new list and
 * deleted users.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/07/2018
 */
public class Store {
    /**
     * The storage for users. Users from the first list puts here. Then checks changes for users from the second list.
     */
    private Map<Integer, String> storage = new HashMap<Integer, String>();

    /**
     * The result of the differences between two lists.
     */
    private Info changes = new Info();

    /**
     * Counts quantity of added users in new list, modified users in new list and
     * deleted users.
     *
     * @param previous the first list.
     * @param current the second list.
     * @return quantity of added users in new list, modified users in new list and deleted users.
     */
    public Info diff(List<User> previous, List<User> current) {
        this.putUsers(previous);
        this.checkUsers(current);
        return this.changes;
    }

    /**
     * Puts users from the first list into the storage.
     *
     * @param users - the first list.
     */
    private void putUsers(List<User> users) {
        for (User user : users) {
            this.storage.put(user.getId(), user.getName());
        }
    }

    /**
     * Checks differences between users in the storage and users in the second list.
     *
     * @param users the second list.
     */
    private void checkUsers(List<User> users) {
        for (User user : users) {
            if (!this.storage.containsKey(user.getId())) {
                this.changes.incrNewUsers();
            } else if (this.storage.containsKey(user.getId()) && !this.storage.get(user.getId()).equals(user.getName())) {
                this.changes.incrModUsers();
            }
        }
        this.changes.delUsers = this.storage.size() - users.size() + this.changes.getNewUsers();
    }

    /**
     * Quantity of new users.
     *
     * @return - the quantity of new users.
     */
    public int getNew() {
        return this.changes.newUsers;
    }

    /**
     * Quantity of modified users.
     *
     * @return - the quantity of modified users.
     */
    public int getMod() {
        return this.changes.modUsers;
    }

    /**
     * Quantity of deleted users.
     *
     * @return - the quantity of deleted users.
     */
    public int getDel() {
        return this.changes.delUsers;
    }

    /**
     * Class fore store the result of differences between two lists.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 13/07/2018
     */
    private static class Info {
        /**
         * New user's quantity.
         */
        private int newUsers;

        /**
         * Modified user's quantity.
         */
        private int modUsers;

        /**
         * Deleted user's quantity.
         */
        private int delUsers;

        /**
         * Increments new users.
         */
        private void incrNewUsers() {
            this.newUsers++;
        }

        /**
         * Increments modified users.
         */
        private void incrModUsers() {
            this.modUsers++;
        }

        /**
         * Gets new user's quantity.
         *
         * @return new user's quantity.
         */
        public int getNewUsers() {
            return this.newUsers;
        }

        /**
         * Gets modified user's quantity.
         *
         * @return modified user's quantity.
         */
        public int getModUsers() {
            return this.modUsers;
        }

        /**
         * Gets deleted user's quantity.
         *
         * @return deleted user's quantity.
         */
        public int getDelUsers() {
            return this.delUsers;
        }
    }

    /**
     * User's description.
     *
     * @author Gregory Smirnov (artress@ngs.ru)
     * @version 1.0
     * @since 13/07/2018
     */
    public static class User {
        /**
         * The unique user's id.
         */
        private final int id;

        /**
         * The user's name.
         */
        private String name;

        /**
         * The constructor creates a new user.
         *
         * @param id - unique id.
         * @param name - user's name.
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Gets user's id.
         *
         * @return the user's id.
         */
        public int getId() {
            return this.id;
        }

        /**
         * Gets the user's name.
         *
         * @return the user's name.
         */
        public String getName() {
            return this.name;
        }
    }
}