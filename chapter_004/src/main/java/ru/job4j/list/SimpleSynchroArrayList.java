package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Simple thread safe array list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 17/05/2018
 */
@ThreadSafe
public class SimpleSynchroArrayList<E> {
    /**
     * The container.
     */
    @GuardedBy("this")
    private final SimpleArrayList<E> list;

    /**
     * Creates simple array list of default size.
     */
    public SimpleSynchroArrayList() {
        this.list = new SimpleArrayList<E>();
    }

    /**
     * Creates simple array list of specified size.
     *
     * @param size - the specified size.
     */
    public SimpleSynchroArrayList(int size) {
        this.list = new SimpleArrayList<E>(size);
    }

    /**
     * Appends the specified element to the end of this array list.
     *
     * @param value - the value to add.
     * @return - true if addition successful.
     */
    public synchronized boolean add(E value) {
        return this.list.add(value);
    }

    /**
     * Appends the specified element to the specified position of this array list.
     *
     * @param index - the specified position.
     * @param value - the value to add.
     */
    public synchronized void add(int index, E value) {
        this.list.add(index, value);
    }

    /**
     * Replaces the element at the specified position in this array list with the specified element.
     *
     * @param index - the specified position.
     * @param value - new element.
     * @return old element.
     */
    public synchronized E set(int index, E value) {
        return this.list.set(index, value);
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index - the specified position.
     * @return the element at the specified position.
     */
    public synchronized E get(int index) {
        return this.get(index);
    }

    /**
     * Removes the element at the specified position in this array list.
     *
     * @param index - the specified position
     * @return removed element.
     */
    public synchronized E remove(int index) {
        return this.list.remove(index);
    }

    /**
     * Gets actual size (without empty cells).
     *
     * @return - the actual size.
     */
    public synchronized int size() {
        return this.list.size();
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return index of the firs occurrence of specified element or -1.
     */
    public synchronized int indexOf(Object o) {
        return this.indexOf(o);
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o - the specified element.
     * @return true if this list contains the specified element.
     */
    public synchronized boolean contains(Object o) {
        return this.list.contains(o);
    }

    /**
     * Returns an iterator over the elements in this array list in proper sequence.
     *
     * @return iterator over Simple Array List.
     */
    public synchronized Iterator<E> iterator() {
        return this.list.iterator();
    }
}