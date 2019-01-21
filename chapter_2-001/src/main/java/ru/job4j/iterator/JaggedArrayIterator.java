package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Describes iterator over jagged array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 30/04/2018
 */
public class JaggedArrayIterator implements Iterator {
    /**
     * The jagged array.
     */
    private final int[][] values;

    /**
     * Current iterator position.
     */
    private int index = 0;

    /**
     * The quantity of all elements, located in this jugged array.
     */
    private int size = 0;

    /**
     * The constructor. Initialize this.values by input jagged array and counts all elements.
     *
     * @param values - input jagged array.
     */
    public JaggedArrayIterator(int[][] values) {
        this.values = values;
        this.countSize();
    }

    /**
     * Counts quantity of all elements, located in this jugged array.
     */
    private void countSize() {
        for (int[] row : this.values) {
            for (int position : row) {
                this.size++;
            }
        }
    }

    /**
     * Checks for next element.
     *
     * @return true if this jugged array contains next element.
     */
    @Override
    public boolean hasNext() {
        return this.size > this.index;
    }

    /**
     * Gets current element and moves pointer forward.
     * If the array is empty, throws NoSuchElementException.
     *
     * @return current element.
     */
    @Override
    public Object next() {
        int elemPointer = this.index;
        Integer result = null;
        for (int row = 0; row < this.values.length; row++) {
            if (elemPointer - this.values[row].length >= 0) {
                elemPointer -= this.values[row].length;
            } else {
                result = this.values[row][elemPointer];
                break;
            }
        }
        this.index++;
        if (result == null) {
            throw new NoSuchElementException("The array is empty!");
        }
        return result;
    }
}