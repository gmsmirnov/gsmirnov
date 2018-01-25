package ru.job4j.array;

import java.util.Arrays;

/**
 * Removing duplicated words from array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 24/01/2018
 */
public class ArrayDuplicate {
    /**
     * Removes duplicated words from array.
     * @param array - the array with duplicated words.
     * @return the array without duplicated words.
     */
    public String[] remove(String[] array) {
        int count = 0; // transposition counter
        String tmp;
        for (int i = 0; i < array.length - count - 1; i++) {
            for (int j = i + 1; j < array.length - count; j++) {
                if (array[i].equals(array[j])) {
                    tmp = array[j];
                    array[j] = array[array.length - count - 1];
                    array[array.length - count - 1] = tmp;
                    j--;
                    count++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - count);
    }
}