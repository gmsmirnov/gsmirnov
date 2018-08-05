package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple thread safe array list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 17/05/2018
 */
@ThreadSafe
public class SimpleSynchroArrayList<E> implements BaseList<E> {
    /**
     * The elements of SimpleArray.
     */
    @GuardedBy("this")
    private Object[] values;

    /**
     * The current position of last element.
     */
    @GuardedBy("this")
    private int index = 0;

    /**
     * Simple array list modification counter.
     */
    @GuardedBy("this")
    private int modCount = 0;

    /**
     * The default size of simple array list.
     */
    private static final int DEFAULT_SIZE = 100;

    /**
     * Creates simple array list of default size.
     */
    public SimpleSynchroArrayList() {
        this.values = new Object[SimpleSynchroArrayList.DEFAULT_SIZE];
    }

    /**
     * Creates simple array list of specified size.
     *
     * @param size - the specified size.
     */
    public SimpleSynchroArrayList(int size) {
        this.values = new Object[size];
    }

    /**
     * Appends the specified element to the end of this array list.
     *
     * @param value - the value to add.
     * @return - true if addition successful.
     */
    @Override
    public synchronized boolean add(E value) {
        if (this.values.length == this.index) {
            this.grow();
        }
        this.modCount++;
        this.values[index++] = value;
        return true;
    }

    /**
     * Appends the specified element to the specified position of this array list.
     *
     * @param index - the specified position.
     * @param value - the value to add.
     */
    @Override
    public synchronized void add(int index, E value) {
        this.rangeCheck(index);
        if (this.values.length == this.index) {
            this.grow();
        }
        this.modCount++;
        System.arraycopy(this.values, index, this.values, index + 1, this.size() - index);
        this.values[index] = value;
        this.index++;
    }

    /**
     * Replaces the element at the specified position in this array list with the specified element.
     *
     * @param index - the specified position.
     * @param value - new element.
     * @return old element.
     */
    @Override
    public synchronized E set(int index, E value) {
        this.rangeCheck(index);
        E oldValue = (E) this.values[index];
        this.values[index] = value;
        return oldValue;
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index - the specified position.
     * @return the element at the specified position.
     */
    @Override
    public synchronized E get(int index) {
        this.rangeCheck(index);
        return (E) this.values[index];
    }

    /**
     * Removes the element at the specified position in this array list.
     *
     * @param index - the specified position
     * @return removed element.
     */
    @Override
    public synchronized E remove(int index) {
        this.rangeCheck(index);
        this.modCount++;
        E oldValue = (E) this.values[index];
        int numMoved = this.index - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.values, index + 1, this.values, index, numMoved);
        }
        this.values[--this.index] = null;
        return oldValue;
    }

    /**
     * Gets actual size (without empty cells).
     *
     * @return - the actual size.
     */
    @GuardedBy("this")
    @Override
    public synchronized int size() {
        return this.index;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return index of the firs occurrence of specified element or -1.
     */
    @Override
    public synchronized int indexOf(Object o) {
        int result = -1;
        if (o == null) {
            for (int index = 0; index < this.size(); index++) {
                if (this.values[index] == null) {
                    result = index;
                }
            }
        } else {
            for (int index = 0; index < this.size(); index++) {
                if (o.equals(this.values[index])) {
                    result = index;
                }
            }
        }
        return result;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o - the specified element.
     * @return true if this list contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Checks index location. If index locates out of array list bounds, then method throws
     * ArrayIndexOutOfBoundsException.
     *
     * @param index - the specified position to check.
     */
    private synchronized void rangeCheck(int index) {
        if (index >= this.values.length) {
            throw new ArrayIndexOutOfBoundsException("Index of out bounds!");
        }
    }

    /**
     * Increases the size of simple array list.
     */
    private synchronized void grow() {
        int oldCapacity = this.values.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newValues = new Object[newCapacity];
        System.arraycopy(this.values, 0, newValues, 0, oldCapacity);
        this.values = newValues;
    }

    /**
     * Returns an iterator over the elements in this array list in proper sequence.
     *
     * @return iterator over Simple Array List.
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleSynchroArrayListIterator<E>();
    }

    /**
     * Describes an iterator over the elements of SimpleSynchroArrayList.
     *
     * @param <E> - Type of elements.
     */
    private class SimpleSynchroArrayListIterator<E> implements Iterator<E> {
        /**
         * The current position of iterator.
         */
        private int position;

        /**
         * Number of expected modifications. If number of modification changes throws exception.
         */
        private int expectedMod;

        /**
         * Creates new iterator and initializes it.
         */
        public SimpleSynchroArrayListIterator() {
            synchronized (SimpleSynchroArrayList.this) {
                this.position = 0;
                this.expectedMod = SimpleSynchroArrayList.this.modCount;
            }
        }

        /**
         * Checks for next element.
         *
         * @return true if this SimpleSynchroArrayList contains next element.
         */
        @Override
        public boolean hasNext() {
            synchronized (SimpleSynchroArrayList.this) {
                return this.position != SimpleSynchroArrayList.this.values.length && SimpleSynchroArrayList.this.values[this.position] != null;
            }
        }

        /**
         * Gets current element and moves pointer forward.
         * If the array list contains no more elements, throws NoSuchElementException.
         *
         * @return current element.
         */
        @Override
        public E next() {
            synchronized (SimpleSynchroArrayList.this) {
                this.modificationCheck();
                E result = (E) SimpleSynchroArrayList.this.values[position++];
                if (result == null) {
                    throw new NoSuchElementException("No more suitable elements!");
                }
                return result;
            }
        }

        /**
         * Checks for modification while iterator is working. If number of modification changes throws exception.
         */
        private void modificationCheck() {
            synchronized (SimpleSynchroArrayList.this) {
                if (this.expectedMod != SimpleSynchroArrayList.this.modCount) {
                    throw new ConcurrentModificationException("The Simple Array List was changed its structure!");
                }
            }
        }
    }
}