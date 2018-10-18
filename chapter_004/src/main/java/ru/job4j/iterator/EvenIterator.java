package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Describes even numbers iterator over array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/05/2018
 */
public class EvenIterator implements Iterator {
    /**
     * The even numbers array.
     */
    private final int[] values;

    /**
     * Current iterator position.
     */
    private int index = 0;

    /**
     * The constructor. Initialize this.values by input even numbers array.
     *
     * @param values - input even numbers array.
     */
    public EvenIterator(int[] values) {
        this.values = values;
    }

    /**
     * Checks for next element.
     *
     * @return true if this even numbers array contains next even element.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.index; i < this.values.length; i++) {
            if (this.values[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Gets current element and moves pointer forward.
     * If the array contains no more even numbers, throws NoSuchElementException.
     *
     * @return current even element.
     */
    @Override
    public Object next() {
        Integer result = null;
        for (int i = this.index; i < this.values.length; i++) {
            if (this.values[i] % 2 == 0) {
                result = this.values[i];
                this.index = i;
                break;
            }
        }
        this.index++;
        if (result == null) {
            throw new NoSuchElementException("No more suitable elements!");
        }
        return result;
    }
}