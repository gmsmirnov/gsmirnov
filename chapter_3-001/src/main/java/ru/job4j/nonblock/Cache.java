package ru.job4j.nonblock;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The Base model's cache.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 10/08/2018
 */
public class Cache {
    /**
     * The container, which stores new and updated models.
     */
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<Integer, Base>();

    /**
     * Gets model by id.
     *
     * @param key - model's id.
     * @return the model with specified id.
     */
    public Base getModel(int key) {
        return this.cache.get(key);
    }

    /**
     * Adds new model to the cache.
     *
     * @param model - new model.
     */
    public void add(Base model) {
        this.cache.computeIfAbsent(model.getId(), v -> model);
    }

    /**
     * Updates the model with specified id. If model's version is the same or lower, throws OptimisticException.
     *
     * @param model - the model with specified id.
     */
    public void update(Base model) throws OptimisticException {
        Base oldModel = this.getModel(model.getId());
        this.cache.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() <= oldModel.getVersion()) {
                throw new OptimisticException("Current version is equal or higher!");
            }
            v = model;
            return v;
        });
    }

    /**
     * Deletes the specified model.
     *
     * @param model - the specified model.
     */
    public void delete(Base model) {
        this.cache.remove(model.getId());
    }

    /**
     * Presents cache in string view.
     *
     * @return the string view.
     */
    @Override
    public String toString() {
        return String.format("Cache{cache=%s}", this.cache);
    }
}