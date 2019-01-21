package ru.job4j.array;

/**
 * Searching an element in array.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 22/01/2018
 * @version 1.0
 */
public class FindLoop {
    /**
     * Searching an element in array.
     * @param data - the array in which the search is performed.
     * @param el - the searching element.
     * @return the index of searching element if it exists in array, or '-1' if not.
     */
    public int indexOf(int[] data, int el) {
        int rsl = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }
}