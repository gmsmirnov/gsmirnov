package ru.job4j.servlets;

import java.util.Collection;

/**
 * Validate service for user servlet.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/02/2019
 */
public class ValidateService {
    /**
     * The singleton instance of a validate service.
     */
    private static ValidateService singletonValidateServiceInstance = new ValidateService();

    /**
     * The storage singleton instance.
     */
    private final Store store = DBStore.getDBStoreInstance();

    /**
     * Default constructor.
     */
    private ValidateService() {
    }

    /**
     * Eager initialization of a validate service.
     *
     * @return the singleton instance of a validate service.
     */
    public static ValidateService getSingletonValidateServiceInstance() {
        return ValidateService.singletonValidateServiceInstance;
    }

    /**
     * Checks if the specified user is already exists in the storage. If not, then puts it into storage.
     *
     * @param user - the specified user.
     * @return true if successful.
     */
    public boolean add(User user) {
        boolean result = false;
        if (!this.store.contains(user)) {
            this.store.add(user);
            result = true;
        }
        return result;
    }

    /**
     * Checks if the specified user is in the storage. If it is, then updates it.
     *
     * @param user - the specified user.
     * @return true if successful.
     */
    public boolean update(User user) {
        boolean result = false;
        if (this.store.containsKey(user)) {
            this.store.update(user);
            result = true;
        }
        return result;
    }

    /**
     * Checks if the specified user is in the storage. If it is, then deletes it.
     *
     * @param user - the specified user.
     * @return true if successful.
     */
    public boolean delete(User user) {
        boolean result = false;
        if (this.store.containsKey(user)) {
            this.store.delete(user);
            result = true;
        }
        return result;
    }

    /**
     * Gets all users from the container.
     *
     * @return a collection of all users.
     */
    public Collection<User> findAll() {
        return this.store.findAll();
    }

    /**
     * Checks if the user with the specified id is in the storage. If it is, then gets it.
     *
     * @param id - the specified id.
     * @return a user which is mapped to the specified id.
     */
    public User findById(int id) {
        User result = null;
        if (this.store.containsKey(id)) {
            result = this.store.findById(id);
        }
        return result;
    }
}