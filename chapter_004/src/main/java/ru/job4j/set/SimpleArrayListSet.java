package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

/**
 * Simple set based on array list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/05/2018
 */
public class SimpleArrayListSet<E> extends SimpleArrayList<E> {
    /**
     * Set based on simple array list, which presents inner container.
     */
    private SimpleArrayList<E> values;

    /**
     * Creates simple set based on array list of default size (100 elements).
     */
    public SimpleArrayListSet() {
        this.values = new SimpleArrayList();
    }

    /**
     * Creates simple set based on array list of specified size.
     */
    public SimpleArrayListSet(int size) {
        this.values = new SimpleArrayList(size);
    }

    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param value - the value to add.
     * @return - true if addition successful.
     */
    @Override
    public boolean add(E value) {
        boolean result = false;
        if (!this.values.contains(value)) {
            this.values.add(value);
            result = true;
        }
        return result;
    }

    /**
     * Returns an iterator over the elements in this set.
     *
     * @return iterator over Simple Set based on Simple Array List.
     */
    @Override
    public Iterator<E> iterator() {
        return this.values.iterator();
    }
}