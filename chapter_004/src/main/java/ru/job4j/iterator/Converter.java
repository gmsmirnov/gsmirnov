package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converts iterator of iterators into simple iterator.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/05/2018
 */
public class Converter {
    /**
     * Converts iterator of iterators into simple iterator.
     *
     * @param its - iterator of iterators.
     * @return simple iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {
        return new Iterator<Integer>() {
            /**
             * Current iterator.
             */
            private Iterator<Integer> currentIt;

            /**
             * True if current iterator is empty;
             */
            private boolean isEmpty = false;

            /**
             * Initiates current iterator.
             */
            private void init() {
                if (this.currentIt == null && its.hasNext()) {
                    this.currentIt = its.next();
                    if (!this.currentIt.hasNext()) {
                        this.isEmpty = true;
                    }
                }
            }

            /**
             * Check for next element.
             *
             * @return true if there is next element.
             */
            @Override
            public boolean hasNext() {
                this.init();
                return (this.currentIt.hasNext() || its.hasNext()) && !this.isEmpty;
            }

            /**
             * Gets next element and move pointer forward.
             *
             * @return next element.
             */
            @Override
            public Integer next() {
                Integer result = null;
                this.init();
                if (currentIt.hasNext()) {
                    result = this.currentIt.next();
                } else if (its.hasNext()) {
                    this.currentIt = its.next();
                    result = this.currentIt.next();
                }
                if (result == null) {
                    throw new NoSuchElementException("No more items!");
                }
                return result;
            }
        };
    }
}