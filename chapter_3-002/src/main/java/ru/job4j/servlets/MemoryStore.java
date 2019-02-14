package ru.job4j.servlets;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of a memory storage.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 07/02/2019
 */
public class MemoryStore implements Store {
    /**
     * Thread sage hash map is a storage for users.
     */
    private final Map<Integer, User> storage = new ConcurrentHashMap<Integer, User>();

    /**
     * The singleton instance of the storage.
     */
    private static MemoryStore singletonMemoryInstance = new MemoryStore();

    /**
     * Default constructor.
     */
    private MemoryStore() {
    }

    /**
     * Eager initialization of the storage singleton instance.
     *
     * @return the singleton instance of the storage.
     */
    public static MemoryStore getSingletonMemoryInstance() {
        return MemoryStore.singletonMemoryInstance;
    }

    /**
     * Puts the specified value into the storage.
     *
     * @param user - the specified value.
     */
    @Override
    public void add(User user) {
        this.storage.put(user.getId(), user);
    }

    /**
     * Replaces the specified value in the storage.
     *
     * @param user - the specified value.
     */
    @Override
    public void update(User user) {
        this.storage.put(user.getId(), user);
    }

    /**
     * Deletes the specified user in the storage.
     *
     * @param user - the specified user.
     */
    @Override
    public void delete(User user) {
        this.storage.remove(user.getId());
    }

    /**
     * Gets a collection of all users in the storage.
     *
     * @return a collection of all users in the storage.
     */
    @Override
    public Collection<User> findAll() {
        return this.storage.values();
    }

    /**
     * Finds the user in the store by the specified id.
     *
     * @param id - the specified id.
     * @return the user which is mapped to the specified id.
     */
    @Override
    public User findById(int id) {
        return this.storage.get(id);
    }

    /**
     * Checks if the specified user is in the container.
     *
     * @param user - the specified user.
     * @return true if the specified user exists in the container.
     */
    @Override
    public boolean contains(User user) {
        return this.storage.containsValue(user);
    }

    /**
     * Checks if the specified user's key is used in the map.
     *
     * @param user - the specified user, which id checks.
     * @return true if the user's id is used like a key.
     */
    public boolean containsKey(User user) {
        return this.storage.containsKey(user.getId());
    }

    /**
     * Checks if the specified id is used in the map.
     *
     * @param id - the specified id.
     * @return true if the id is used like a key.
     */
    public boolean containsKey(int id) {
        return this.storage.containsKey(id);
    }
}