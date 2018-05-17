package ru.job4j.generic;

/**
 * Abstract storage. Describes storage of elements extended from Base.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/05/2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * The storage.
     */
    protected final SimpleArray<T> store;

    /**
     * Creates the storage of specified size.
     *
     * @param size - the size of storage.
     */
    public AbstractStore(int size) {
        this.store = new SimpleArray<T>(size);
    }

    /**
     * Adds new element to the storage.
     *
     * @param model - new element.
     */
    public void add(T model) {
        this.store.add(model);
    }

    /**
     * Replaces the first old element with specified id in the storage by the specified element.
     *
     * @param id - the id of replaceable element.
     * @param model - new element.
     * @return true if success.
     */
    public boolean replace(String id, T model) {
        boolean result = false;
        int position = 0;
        for (T user : this.store) {
            if (user.getId().equals(id)) {
                this.store.set(position, model);
                result = true;
                break;
            }
            position++;
        }
        return result;
    }

    /**
     * Deletes the first old element with specified id.
     *
     * @param id - the id of deletable ekement.
     * @return true if success.
     */
    public boolean delete(String id) {
        boolean result = false;
        int position = 0;
        for (T user : this.store) {
            if (user.getId().equals(id)) {
                this.store.remove(position);
                result = true;
                break;
            }
            position++;
        }
        return result;
    }

    /**
     * Finds the first element with specified id.
     *
     * @param id - the specified id.
     * @return - the element with specified id if it exists, null otherwise.
     */
    public T findById(String id) {
        T result = null;
        int position = 0;
        for (T user : this.store) {
            if (user.getId().equals(id)) {
                result = this.store.get(position);
                break;
            }
            position++;
        }
        return result;
    }
}