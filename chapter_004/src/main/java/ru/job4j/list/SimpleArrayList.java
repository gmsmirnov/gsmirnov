package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple array list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/05/2018
 */
public class SimpleArrayList<E> implements Iterable<E> {
    /**
     * The elements of SimpleArray.
     */
    private Object[] values;

    /**
     * The current position of last element.
     */
    private int index = 0;

    /**
     * Simple array list modification counter.
     */
    private int modCount = 0;

    /**
     * The default size of simple array list.
     */
    private static final int DEFAULT_SIZE = 100;

    /**
     * Creates simple array list of default size.
     */
    public SimpleArrayList() {
        this.values = new Object[SimpleArrayList.DEFAULT_SIZE];
    }

    /**
     * Creates simple array list of specified size.
     *
     * @param size - the specified size.
     */
    public SimpleArrayList(int size) {
        this.values = new Object[size];
    }

    /**
     * Appends the specified element to the end of this array list.
     *
     * @param value - the value to add.
     * @return - true if addition successful.
     */
    public boolean add(E value) {
        if (this.values.length == this.index) {
            this.grow();
        }
        this.modCount++;
        this.values[index++] = value;
        return true;
    }

    /**
     * Replaces the element at the specified position in this array list with the specified element.
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
     * Removes the element at the specified position in this array list.
     *
     * @param index - the specified position
     * @return removed element.
     */
    public E remove(int index) {
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
     * Checks index location. If index locates out of array list bounds, then method throws
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
     * Increases the size of simple array list.
     */
    private void grow() {
        int oldCapacity = this.values.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newValues = new Object[newCapacity];
        System.arraycopy(this.values, 0, newValues, 0, oldCapacity);
        this.values = newValues;
    }

    /**
     * Gets actual size (without empty cells).
     *
     * @return - the actual size.
     */
    public int getActualSize() {
        return this.index;
    }

    /**
     * Returns an iterator over the elements in this array list in proper sequence.
     *
     * @return iterator over Simple Array List.
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayListIterator<E>();
    }

    /**
     * Describes an iterator over the elements of SimpleArrayList.
     *
     * @param <E> - Type of elements.
     */
    private class SimpleArrayListIterator<E> implements Iterator<E> {
        /**
         * The current position of iterator.
         */
        private int position = 0;

        /**
         * Number of expected modifications. If number of modification changes throws exception.
         */
        private int expectedMod = modCount;

        /**
         * Checks for next element.
         *
         * @return true if this SimpleArrayList contains next element.
         */
        @Override
        public boolean hasNext() {
            return this.position != values.length && values[this.position] != null;
        }

        /**
         * Gets current element and moves pointer forward.
         * If the array list contains no more elements, throws NoSuchElementException.
         *
         * @return current element.
         */
        @Override
        public E next() {
            this.modificationCheck();
            E result = (E) values[position++];
            if (result == null) {
                throw new NoSuchElementException("No more suitable elements!");
            }
            return result;
        }

        /**
         * Checks for modification while iterator is working. If number of modification changes throws exception.
         */
        private void modificationCheck() {
            if (this.expectedMod != modCount) {
                throw new ConcurrentModificationException("The Simple Array List was changed its structure!");
            }
        }
    }
}