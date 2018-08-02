package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User's storage.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/08/2018
 */
@ThreadSafe
public class UserStorage {
    /**
     * The user's storage. User's id is the key, user's amount is the value.
     */
    @GuardedBy("this")
    private final Map<Integer, Integer> storage;

    /**
     * Creates new users storage.
     *
     * @param capacity - the storage capacity.
     */
    public UserStorage(int capacity) {
         this.storage = new HashMap<Integer, Integer>(capacity);
    }

    /**
     * Adds new user to the storage.
     *
     * @param user - new user.
     * @return true if addiction is successful.
     */
    public synchronized boolean add(User user) {
        boolean result = false;
        if (!this.storage.containsKey(user.getId())) {
            this.storage.put(user.getId(), user.getAmount());
            result = true;
        }
        return result;
    }

    /**
     * Updates amount of the specified user.
     *
     * @param user - the specified user.
     * @return - true if update is successful.
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        if (this.storage.containsKey(user.getId())) {
            this.storage.put(user.getId(), user.getAmount());
            result = true;
        }
        return result;
    }

    /**
     * Deletes the specified user to the storage.
     *
     * @param user - the specified user.
     * @return - true if delete is successful.
     */
    public synchronized boolean delete(User user) {
        boolean result = false;
        if (this.storage.containsKey(user.getId())) {
            this.storage.remove(user.getId());
            result = true;
        }
        return result;
    }

    /**
     * Transfers the specified amount from one user to another.
     *
     * @param fromId - the id of the first user (from who transfers).
     * @param toId - the id of the second user (to who transfers).
     * @param amount - the specified amount.
     * @return - true if transaction is successful.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result;
        if (!this.storage.containsKey(fromId) || !this.storage.containsKey(toId)
                || fromId == toId || this.storage.get(fromId) < amount) {
            result = false;
        } else {
            this.storage.put(fromId, this.storage.get(fromId) - amount);
            this.storage.put(toId, this.storage.get(toId) + amount);
            result = true;
        }
        return result;
    }

    /**
     * The storage size (the users quantity).
     *
     * @return the quantity of users.
     */
    public int size() {
        return this.storage.size();
    }

    /**
     * Calculates total amounts of all users.
     *
     * @return - the total amount.
     */
    public synchronized int total() {
        Collection<Integer> amounts = this.storage.values();
        int sum = 0;
        for (int i : amounts) {
            sum += i;
        }
        return sum;
    }
}