package ru.job4j.nonblock;

import java.util.concurrent.Callable;

/**
 * A thread which updates models.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/08/2018
 */
public class UpdateTask implements Callable<Base> {
    /**
     * The cache with models.
     */
    private final Cache cache;

    /**
     * The model to update.
     */
    private Base model;

    /**
     * Creates new UpdateTask.
     *
     * @param cache - cache with models.
     * @param id - new model's id.
     */
    public UpdateTask(Cache cache, int id) {
        this.cache = cache;
        this.model = this.cache.getModel(id);
    }

    /**
     * Creates new model with higher version and update the cache.
     */
    public void update() throws OptimisticException {
        this.model = new Base(this.model.getId(), this.model.getVersion() + 1);
        this.cache.update(this.model);
    }

    /**
     * Thread's body.
     */
    @Override
    public Base call() throws Exception {
        try {
            this.update();
        } catch (OptimisticException oe) {
            throw new OptimisticException("Current version is equal or higher!");
        }
        return this.model;
    }
}