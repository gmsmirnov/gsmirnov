package ru.job4j.generic;

/**
 * The role storage.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/05/2018
 */
public class RoleStore<T extends Role> extends AbstractStore<T> {
    /**
     * Creates the storage for Role-type elements of specified size.
     *
     * @param size - the specified size.
     */
    public RoleStore(int size) {
        super(size);
    }
}