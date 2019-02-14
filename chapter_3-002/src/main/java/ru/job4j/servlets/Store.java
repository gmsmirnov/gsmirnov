package ru.job4j.servlets;

import java.util.Collection;

/**
 * Storage interface.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 07/02/2019
 */
public interface Store {
    /**
     * Adds the specified user to the container.
     *
     * @param user - the specified user.
     */
    void add(User user);

    /**
     * Updates the specified user in the container.
     *
     * @param user - the specified user.
     */
    void update(User user);

    /**
     * Deletes the specified user in the container.
     *
     * @param user - the specified user.
     */
    void delete(User user);

    /**
     * Gets a collection of all users in the storage.
     *
     * @return a collection of all users in the storage.
     */
    Collection<User> findAll();

    /**
     * Finds the user in the store by the specified id.
     *
     * @param id - the specified id.
     * @return the user which is mapped to the specified id.
     */
    User findById(int id);

    /**
     * Checks if the specified user is in the container.
     *
     * @param user - the specified user.
     * @return true if the specified user exists in the container.
     */
    boolean contains(User user);
}