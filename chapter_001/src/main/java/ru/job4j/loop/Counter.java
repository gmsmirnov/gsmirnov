package ru.job4j.loop;

/**
 * Counting the sum of even numbers in the range.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 18/01/2018
 * @version 1.0
 */

public class Counter {

    /**
     * Method, which count sum of even numbers in the range.
     * @param start - the first number in the range.
     * @param finish - the last numbers in the range.
     * @return the sum of even numbers in the range.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}