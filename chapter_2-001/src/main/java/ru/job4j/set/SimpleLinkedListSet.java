package ru.job4j.set;

import java.util.Iterator;

public class SimpleLinkedListSet<E> extends ru.job4j.list.SimpleLinkedList<E> {
    /**
     * Set based on simple linked list, which presents inner container.
     */
    private ru.job4j.list.SimpleLinkedList<E> values;

    /**
     * Creates simple set based on simple linked list.
     */
    public SimpleLinkedListSet() {
        this.values = new ru.job4j.list.SimpleLinkedList();
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
     * Try to add the specified element to specified position.
     *
     * @param index - the specifies position.
     * @param value - the specified element.
     */
    @Override
    public void add(int index, E value) {
        if (!this.values.contains(value)) {
            this.values.add(index, value);
        }
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o - the specified element.
     * @return true if this list contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        boolean result = false;
        Iterator<E> it = this.values.iterator();
        while (it.hasNext()) {
            if (it.next().equals(o)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     */
    @Override
    public int indexOf(Object o) {
        int result = -1;
        int counter = 0;
        Iterator<E> it = this.values.iterator();
        while (it.hasNext()) {
            if (it.next().equals(o)) {
                result = counter;
                break;
            }
            counter++;
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

    /**
     * Represents this collection in string-view.
     *
     * @return - presentation in string-view.
     */
    @Override
    public String toString() {
        return "SimpleLinkedListSet{"
                + "values=" + this.values
                + '}';
    }
}