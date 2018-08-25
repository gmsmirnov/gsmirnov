package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Simple thread safe array list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.3
 * @since 17/05/2018
 */
@ThreadSafe
public class SimpleSynchroArrayList<E> {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = this.rwLock.readLock();
    private final Lock wLock = this.rwLock.writeLock();

    /**
     * The container.
     */
    @GuardedBy("this.rwLock")
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
    public boolean add(E value) {
        this.wLock.lock();
        try {
            return this.list.add(value);
        } finally {
          this.wLock.unlock();
        }
    }

    /**
     * Appends the specified element to the specified position of this array list.
     *
     * @param index - the specified position.
     * @param value - the value to add.
     */
    public void add(int index, E value) {
        this.wLock.lock();
        try {
            this.list.add(index, value);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Replaces the element at the specified position in this array list with the specified element.
     *
     * @param index - the specified position.
     * @param value - new element.
     * @return old element.
     */
    public E set(int index, E value) {
        this.wLock.lock();
        try {
            return this.list.set(index, value);
        } finally {
            this.wLock.unlock();
        }
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index - the specified position.
     * @return the element at the specified position.
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
     * Removes the element at the specified position in this array list.
     *
     * @param index - the specified position
     * @return removed element.
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
     * Gets actual size (without empty cells).
     *
     * @return - the actual size.
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
     * Represents the list in string view.
     *
     * @return the string view.
     */
    @Override
    public String toString() {
        this.rLock.lock();
        try {
            return String.format("SimpleSynchroArrayList{list=%s}", list);
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Returns an iterator over the snapshot in this array list in proper sequence.
     *
     * @return iterator over Simple Array List.
     */
    public Iterator<E> iterator() {
        this.rLock.lock();
        try {
            return new SimpleSynchroArrayListIterator<E>();
        } finally {
            this.rLock.unlock();
        }
    }

    /**
     * Describes an iterator over the snapshot of elements of the list. The iterator is fail-safe.
     *
     * @param <E> - Type of elements.
     */
    private class SimpleSynchroArrayListIterator<E> implements Iterator<E> {
        /**
         * The snapshot of collection.
         */
        private final Object[] snapshot;

        /**
         * Current position
         */
        private int cursor;

        /**
         * The constructor.
         */
        public SimpleSynchroArrayListIterator() {
            this.snapshot = SimpleSynchroArrayList.this.list.toArray();
            this.cursor = 0;
        }

        /**
         * Checks for next element.
         *
         * @return true if this snapshot contains next element.
         */
        @Override
        public boolean hasNext() {
            return this.cursor != this.snapshot.length && this.snapshot[cursor] != null;
        }

        /**
         * Gets current element and moves pointer forward.
         * If the array list contains no more elements, throws NoSuchElementException.
         *
         * @return current element.
         */
        @Override
        public E next() {
            E result = (E) this.snapshot[this.cursor++];
            if (result == null) {
                throw new NoSuchElementException("No more suitable snapshot!");
            }
            return result;
        }
    }
}