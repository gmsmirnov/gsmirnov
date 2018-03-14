package ru.job4j.comparison;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sorting users.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
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
}