package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Class Tracker - class wrapper, that manage requests.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.3
 * @since 07/02/2018
 */
public class Tracker {
    /**
     * Container for requests.
     */
    private final ArrayList<Item> items = new ArrayList<Item>();


    /**
     * Addition request to container.
     *
     * @param item - new request that adding to container.
     * @return item with its id in container.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Replaces one request by another request by id.
     *
     * @param id - the id of replacement request.
     * @param newItem - new request that replaces older request.
     * @return true if request was edited successfully, false if there is no request with such id.
     */
    public boolean replace(String id, Item newItem) {
        boolean result = false;
        newItem.setId(id);
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i) != null && this.items.get(i).getId().equals(id)) {
                this.items.set(i, newItem);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Deletes one request by id.
     *
     * @return true if request was delete successfully, false if there is no request with such id.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                this.items.remove(item);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Finds all not null requests.
     *
     * @return new container, which contains all not null requests.
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> result = new ArrayList<Item>();
        for (Item item : this.items) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Finds all requests by name.
     *
     * @param name - the name of needed requests.
     * @return new container, which contains all requests with specified name.
     */
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> result = new ArrayList<Item>();
        for (Item item: this.items) {
            if ((item != null) && (item.getName().equals(name))) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Finds request by id.
     *
     * @param id - the id of needed requests.
     * @return the request with specified id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Generates id for new request.
     *
     * @return generated id.
     */
    private String generateId() {
        return UUID.randomUUID().toString();
    }
}