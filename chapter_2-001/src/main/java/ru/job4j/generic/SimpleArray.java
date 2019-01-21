package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple array with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/05/2018
 */
public class SimpleArray<E> implements Iterable<E> {
    /**
     * The elements of SimpleArray.
     */
    private final Object[] values;

    /**
     * The current position of last element.
     */
    private int index = 0;

    /**
     * Creates simple array of specified size.
     *
     * @param size - the specified size.
     */
    public SimpleArray(int size) {
        this.values = new Object[size];
    }

    /**
     * Appends the specified element to the end of this array.
     *
     * @param value - the value to add.
     * @return - true if addition successful.
     */
    public boolean add(E value) {
        this.rangeCheck(this.index);
        this.values[index++] = value;
        return true;
    }

    /**
     * Replaces the element at the specified position in this array with the specified element.
     *
     * @param index - the specified position.
     * @param value - new element.
     * @return old element.
     */
    public E set(int index, E value) {
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
    public E get(int index) {
        this.rangeCheck(index);
        return (E) this.values[index];
    }

    /**
     * Removes the element at the specified position in this array.
     *
     * @param index - the specified position
     * @return removed element.
     */
    public E remove(int index) {
        this.rangeCheck(index);
        E oldValue = (E) this.values[index];
        int numMoved = this.index - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.values, index + 1, this.values, index, numMoved);
        }
        this.values[--this.index] = null;
        return oldValue;
    }

    /**
     * Checks index location. If index locates out of array bounds, then method throws
     * ArrayIndexOutOfBoundsException.
     *
     * @param index - the specified position to check.
     */
    private void rangeCheck(int index) {
        if (index >= this.values.length) {
            throw new ArrayIndexOutOfBoundsException("Index of out bounds!");
        }
    }

    /**
     * Returns an iterator over the elements in this array in proper sequence.
     *
     * @return iterator over Simple Array.
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayIterator<E>();
    }

    /**
     * Describes an iterator over the elements of SimpleArray.
     *
     * @param <E> - Type of elements.
     */
    private class SimpleArrayIterator<E> implements Iterator<E> {
        /**
         * The current position of iterator.
         */
        private int position = 0;

        /**
         * Checks for next element.
         *
         * @return true if this SimpleArray contains next element.
         */
        @Override
        public boolean hasNext() {
            return this.position != values.length && values[this.position] != null;
        }

        /**
         * Gets current element and moves pointer forward.
         * If the array contains no more elements, throws NoSuchElementException.
         *
         * @return current element.
         */
        @Override
        public E next() {
            E result = (E) values[position++];
            if (result == null) {
                throw new NoSuchElementException("No more suitable elements!");
            }
            return result;
        }
    }
}