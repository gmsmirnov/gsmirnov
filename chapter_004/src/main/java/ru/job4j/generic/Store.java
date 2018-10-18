package ru.job4j.generic;

/**
 * Store interface. Can contain elements extending Base type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/05/2018
 */
public interface Store<T extends Base> {
    /**
     * Adds new element to the storage.
     *
     * @param model - new element.
     */
    void add(T model);

    /**
     * Replaces the first old element with specified id in the storage by the specified element.
     *
     * @param id - the id of replaceable element.
     * @param model - new element.
     * @return true if success.
     */
    boolean replace(String id, T model);

    /**
     * Deletes the first old element with specified id.
     *
     * @param id - the id of deletable ekement.
     * @return true if success.
     */
    boolean delete(String id);

    /**
     * Finds the first element with specified id.
     *
     * @param id - the specified id.
     * @return - the element with specified id if it exists, null otherwise.
     */
    T findById(String id);
}