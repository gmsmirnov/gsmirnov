package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Describes prime numbers iterator over array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/05/2018
 */
public class PrimeIterator implements Iterator {
    /**
     * The prime numbers array.
     */
    private final int[] values;

    /**
     * Current iterator position.
     */
    private int index = 0;

    /**
     * The constructor. Initialize this.values by input prime numbers array.
     *
     * @param values - input prime numbers array.
     */
    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * Checks number for primal number.
     *
     * @param number - input number, which needs to check.
     * @return true if number is primal, false otherwise.
     */
    private boolean isPrimal(int number) {
        boolean result = true;
        if (number < 2) {
            result = false;
        }
        for (int pointer = 2; pointer <= Math.sqrt(number); pointer++) {
            if (number % pointer == 0) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Checks for next element.
     *
     * @return true if this prime numbers array contains next prime element.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int pointer = this.index; pointer < this.values.length; pointer++) {
            if (this.isPrimal(this.values[pointer])) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Gets current element and moves pointer forward.
     * If the array contains no more prime numbers, throws NoSuchElementException.
     *
     * @return current prime element.
     */
    @Override
    public Object next() {
        Integer result = null;
        for (int pointer = this.index; pointer < this.values.length; pointer++) {
            if (this.isPrimal(this.values[pointer])) {
                result = this.values[pointer];
                this.index = pointer;
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