package ru.job4j.list;

import java.util.*;

/**
 * Simple array list with generic type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/05/2018
 */
public class SimpleArrayList<E> implements BaseList<E> {
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
    @Override
    public boolean add(E value) {
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
    public void add(int index, E value) {
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
    @Override
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
    @Override
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
     * Gets actual size (without empty cells).
     *
     * @return - the actual size.
     */
    @Override
    public int size() {
        return this.index;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     *
     * @param o - the specified element.
     * @return index of the firs occurrence of specified element or -1.
     */
    @Override
    public int indexOf(Object o) {
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
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list.
     */
    public Object[] toArray() {
        return Arrays.copyOf(this.values, this.index);
    }

    @Override
    public String toString() {
        return "SimpleArrayList{" +
                "values=" + Arrays.toString(values) +
                '}';
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