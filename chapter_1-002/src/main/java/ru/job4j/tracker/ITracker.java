package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Interface for Tracker - the wrapper, that manage requests.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 18/10/2018
 */
public interface ITracker {
    /**
     * Addition request to container.
     *
     * @param item - new request that adding to container.
     * @return item with its id in container.
     */
    Item add(Item item);

    /**
     * Replaces one request by another request by id.
     *
     * @param id - the id of replacement request.
     * @param newItem - new request that replaces older request.
     * @return true if request was edited successfully, false if there is no request with such id.
     */
    boolean replace(String id, Item newItem);

    /**
     * Deletes one request by id.
     *
     * @return true if request was delete successfully, false if there is no request with such id.
     */
    boolean delete(String id);

    /**
     * Finds all not null requests.
     *
     * @return new container, which contains all not null requests.
     */
    ArrayList<Item> findAll();

    /**
     * Finds all requests by name.
     *
     * @param name - the name of needed requests.
     * @return new container, which contains all requests with specified name.
     */
    ArrayList<Item> findByName(String name);

    /**
     * Finds request by id.
     *
     * @param id - the id of needed requests.
     * @return the request with specified id.
     */
    Item findById(String id);
}