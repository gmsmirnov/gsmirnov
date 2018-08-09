package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Simple thread safe linked list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.4
 * @since 18/05/2018
 */
@ThreadSafe
public class SimpleSynchroLinkedList<E> implements BaseList<E> {
    /**
     * The container.
     */
    @GuardedBy("this")
    private final SimpleLinkedList<E> list;

    public SimpleSynchroLinkedList() {
        this.list = new SimpleLinkedList<E>();
    }

    /**
     * Adds new element into the end of the list.
     *
     * @param value - new element.
     */
    public synchronized boolean add(E value) {
        return this.list.add(value);
    }

    /**
     * Adds new element into the specified position of the list.
     *
     * @param data - new element.
     */
    public synchronized void add(int index, E data) {
        this.list.add(index, data);
    }

    /**
     * Replaces the element at the specified position in this linked list with the specified element.
     *
     * @param index - the specified position.
     * @param data - new element.
     * @return old element.
     */
    public synchronized E set(int index, E data) {
        return this.list.set(index, data);
    }

    /**
     * Gets the element in the specified position.
     *
     * @param index - the specified position.
     * @return - the element in specified position.
     */
    public synchronized E get(int index) {
        return this.list.get(index);
    }

    /**
     * Deletes the first element of the list.
     *
     * @return - deleted element.
     */
    public synchronized E remove(int index) {
        return this.list.remove(index);
    }

    /**
     * Gets the size of the list.
     *
     * @return - the size of the list.
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
     * Returns an iterator over the elements in this linked list in proper sequence.
     *
     * @return iterator over Simple Linked List.
     */
    public synchronized Iterator<E> iterator() {
        return this.list.iterator();
    }

    /**
     * Represents this collection in string-view.
     *
     * @return - presentation in string-view.
     */
    public synchronized String toString() {
        return this.list.toString();
    }
}