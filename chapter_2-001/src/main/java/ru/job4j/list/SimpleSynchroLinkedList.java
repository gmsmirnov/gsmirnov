package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simple thread safe linked list with generic type. It can produce non-mutable operations in parallel threads.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.4
 * @since 18/05/2018
 */
@ThreadSafe
public class SimpleSynchroLinkedList<E> implements BaseList<E> {
    /**
     * The lock-object, which generates two locks: read-lock for non-mutable operations,
     * write-lock for mutable operations. In one time uses only one write-lock or many read locks.
     * Thereby non-mutable operations can be produced simultaneously.
     */
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * The lock for non-mutable operations.
     */
    private final Lock rLock = this.rwLock.readLock();

    /**
     * The lock for mutable operations.
     */
    private final Lock wLock = this.rwLock.writeLock();

    /**
     * The container.
     */
    @GuardedBy("this.rwLock")
    private final SimpleLinkedList<E> list;

    /**
     * Creates new list based on SimpleLinkedList.
     */
    public SimpleSynchroLinkedList() {
        this.list = new SimpleLinkedList<E>();
    }

    /**
     * Adds new element into the end of the list.
     *
     * @param value - new element.
     */
    public boolean add(E value) {
        this.wLock.lock();
        try {
            return this.list.add(value);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Adds new element into the specified position of the list.
     *
     * @param data - new element.
     */
    public void add(int index, E data) {
        this.wLock.lock();
        try {
            this.list.add(index, data);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Replaces the element at the specified position in this linked list with the specified element.
     *
     * @param index - the specified position.
     * @param data - new element.
     * @return old element.
     */
    public E set(int index, E data) {
        this.wLock.lock();
        try {
            return this.list.set(index, data);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Gets the element in the specified position.
     *
     * @param index - the specified position.
     * @return - the element in specified position.
     */
    public E get(int index) {
        this.rLock.lock();
        try {
            return this.list.get(index);
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Deletes the first element of the list.
     *
     * @return - deleted element.
     */
    public E remove(int index) {
        this.wLock.lock();
        try {
            return this.list.remove(index);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Gets the size of the list.
     *
     * @return - the size of the list.
     */
    public int size() {
        this.rLock.lock();
        try {
            return this.list.size();
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return index of the firs occurrence of specified element or -1.
     */
    public int indexOf(Object o) {
        this.rLock.lock();
        try {
            return this.list.indexOf(o);
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o - the specified element.
     * @return true if this list contains the specified element.
     */
    public boolean contains(Object o) {
        this.rLock.lock();
        try {
            return this.list.contains(o);
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Returns an iterator over the elements in this linked list in proper sequence.
     *
     * @return iterator over Simple Linked List.
     */
    public Iterator<E> iterator() {
        this.rLock.lock();
        try {
            return this.copy().iterator();
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Weakly fair copy of this list. Uses for fail safe iterator.
     *
     * @return weakly fair copy of this list.
     */
    private SimpleLinkedList<E> copy() {
        SimpleLinkedList<E> newList = new SimpleLinkedList<E>();
        this.list.forEach(newList::add);
        return newList;
    }

    /**
     * Returns an array containing all of the snapshot in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the snapshot in this list.
     */
    public Object[] toArray() {
        this.rLock.lock();
        try {
            return this.list.toArray();
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Represents this collection in string-view.
     *
     * @return - presentation in string-view.
     */
    public String toString() {
        this.rLock.lock();
        try {
            return this.list.toString();
        } finally {
            this.rLock.unlock();
        }
    }
}