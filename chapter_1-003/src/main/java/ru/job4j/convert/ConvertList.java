package ru.job4j.convert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Converts two-dimensional array to ArrayList and ArrayList to two-dimensional array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/03/2018
 */
public class ConvertList {
    /**
     * Converts two-dimensional array to ArrayList.
     *
     * @param array - two-dimensional array.
     * @return ArrayList.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<Integer>();
        for (int[] row : array) {
            for (int index : row) {
                list.add(index);
            }
        }
        return list;
    }

    /**
     * Converts ArrayList to two-dimensional array.
     *
     * @param list - the ArrayList.
     * @param rows - the rows quantity in two-dimensional array.
     * @return - two-dimensional array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result;
        int columns;
        if (list.size() % rows != 0) {
            columns = list.size() / rows + 1;
        } else {
            columns = list.size() / rows;
        }
        result = new int[rows][columns];
        int index = 0;
        int row = 0;
        for (int element : list) {
            result[row][index++] = element;
            if (index % columns == 0) {
                row++;
                index = 0;
            }
        }
        return result;
    }

    /**
     * Converts list of Integer arrays to list of Integer.
     *
     * @param list - the list of Integer arrays.
     * @return - converted Integer list.
     */
    public List<Integer> convert(List<Integer[]> list) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer[] row : list) {
            for (Integer element : row) {
                result.add(element);
            }
        }
        return result;
    }
}