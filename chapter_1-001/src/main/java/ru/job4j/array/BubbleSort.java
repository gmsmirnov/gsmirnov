package ru.job4j.array;

/**
 * Sorting array. 'Babble sort'.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 23/01/2018
 * @version 1.0
 */
public class BubbleSort {
    /**
     * Sorting array. 'Bubble sort'.
     * @param array - the input not sorted array.
     * @return the sorted by 'Bubble sort' array.
     */
    public int[] sort(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}