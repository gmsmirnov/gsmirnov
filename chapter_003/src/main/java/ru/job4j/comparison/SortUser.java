package ru.job4j.comparison;

import java.util.*;

/**
 * Sorting users.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 13/03/2018
 */
public class SortUser {
    /**
     * The source list of users.
     */
    private final List<User> users = new ArrayList<User>();

    /**
     * Sorts users.
     *
     * @param users - source list of users.
     * @return - sorted list of users.
     */
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<User>();
        result.addAll(users);
        return result;
    }

    /**
     * Adds user to source list.
     *
     * @param user - new user.
     */
    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * Gets source list.
     *
     * @return - source list of users.
     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Sorts users by the name length. If name lengths are equals, sorts users by ages.
     *
     * @param users - the list of users.
     * @return - the sorted list of users.
     */
    public List<User> sortNameLength(List<User> users) {
        List<User> sortedUsers = new ArrayList<User>();
        sortedUsers.addAll(users);
        sortedUsers.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                int result = 0;
                if (user1 != null && user2 != null) {
                    if (user1.equals(user2)) {
                        result = 0;
                    } else if (user1.getName().length() > user2.getName().length()) {
                        result = 1;
                    } else if (user1.getName().length() < user2.getName().length()) {
                        result = -1;
                    }
                }
                return result;
            }
        });
        return sortedUsers;
    }

    /**
     * Sorts users by names in lexicographical order. If names are equals, sorts users by ages.
     *
     * @param users - the list of users.
     * @return - the sorted list of users.
     */
    public List<User> sortByAllFields(List<User> users) {
        List<User> sortedUsers = new ArrayList<User>();
        sortedUsers.addAll(users);
        sortedUsers.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                int result = 0;
                if (user1 != null && user2 != null) {
                    if (user1.equals(user2)) {
                        result = 0;
                    } else if (user1.getName().equals(user2.getName()) && user1.getAge() > user2.getAge()) {
                        result = 1;
                    } else if (user1.getName().equals(user2.getName()) && user1.getAge() < user2.getAge()) {
                        result = -1;
                    } else {
                        result = user1.getName().compareTo(user2.getName());
                    }
                }
                return result;
            }
        });
        return sortedUsers;
    }
}