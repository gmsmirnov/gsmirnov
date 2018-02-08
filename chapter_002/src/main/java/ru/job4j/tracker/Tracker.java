package ru.job4j.tracker;

import java.util.Arrays;
import java.util.UUID;

/**
 * Class Tracker - class wrapper, that manage requests.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 07/02/2018
 */
public class Tracker {
    /**
     * Container for requests.
     */
    private Item[] items = new Item[100];

    /**
     * The position of the last request in the container.
     */
    private int position = 0;

    /**
     * Addition request to container.
     *
     * @param item - new request that adding to container.
     * @return item with its id in container.
     */
    public Item add(Item item) {
        this.position %= 100;
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Replaces one request by another request by id.
     *
     * @param id - the id of replacement request.
     * @param item - new request that replaces older request.
     */
    public void replace(String id, Item item) {
        item.setId(id);
        for (int i = 0; i < this.items.length; i++) {
            if ((this.items[i] != null) && (this.items[i].getId().equals(id))) {
                this.items[i] = item;
                break;
            }
        }
    }

    /**
     * Deletes one request by id.
     *
     */
    public void delete(String id) {
        for (int i = 0; i < this.items.length; i++) {
            if ((this.items[i] != null) && (this.items[i].getId().equals(id))) {
                System.arraycopy(this.items, i + 1, this.items, i, this.position - i);
                position--;
                break;
            }
        }
    }

    /**
     * Finds all not null requests.
     *
     * @return new container, which contains all not null requests.
     */
    public Item[] findAll() {
        Item[] temp = new Item[100];
        int pos = 0;
        for (Item item : this.items) {
            if (item != null) {
                temp[pos++] = item;
            }
        }
        return Arrays.copyOf(temp, pos);
    }

    /**
     * Finds all requests by name.
     *
     * @param name - the name of needed requests.
     * @return new container, which contains all requests with specified name.
     */
    public Item[] findByName(String name) {
        Item[] temp = new Item[100];
        int pos = 0;
        for (Item item: this.items) {
            if ((item != null) && (item.getName().equals(name))) {
                temp[pos++] = item;
            }
        }
        return Arrays.copyOf(temp, pos);
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
            if ((item != null) && (item.getId().equals(id))) {
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
        return String.valueOf(System.currentTimeMillis()) + "-" + UUID.randomUUID();
    }
}