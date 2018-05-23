package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;

/**
 * Simple set based on linked list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/05/2018
 */
public class SimpleLinkedListSet<E> extends SimpleLinkedList<E> {
    /**
     * Set based on simple linked list, which presents inner container.
     */
    private SimpleLinkedList<E> values;

    /**
     * Creates simple set based on simple linked list.
     */
    public SimpleLinkedListSet() {
        this.values = new SimpleLinkedList();
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
     * @return iterator over Simple Set based on Simple Linked List.
     */
    @Override
    public Iterator<E> iterator() {
        return this.values.iterator();
    }
}