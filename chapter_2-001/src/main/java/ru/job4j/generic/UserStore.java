package ru.job4j.generic;

/**
 * The user storage.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/05/2018
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Creates the storage for User-type elements of specified size.
     *
     * @param size - the specified size.
     */
    public UserStore(int size) {
        super(size);
    }
}