package ru.job4j.convert;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;

/**
 * Tests conversion of two-dimensional array into ArrayList and backward.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/03/2018
 */
public class ConvertListTest {
    @Test
    public void whenConvertsTwoDimensionalArrayToArrayList() {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrayList<Integer> result = (ArrayList<Integer>) new ConvertList().toList(array);
        ArrayList<Integer> expected = new ArrayList<Integer>();
        for (int i = 0; i < result.size(); i++) {
            expected.add(i + 1);
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertsArrayListToTwoDimensionalArray() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 13; i++) {
            list.add(i + 1);
        }
        int[][] result = new ConvertList().toArray(list, 4);
        int[][] expected = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 0, 0, 0}};
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertsArrayListToTwoDimensionalArray2() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 13; i++) {
            list.add(i + 1);
        }
        int[][] result = new ConvertList().toArray(list, 3);
        int[][] expected = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 0, 0}};
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertsArrayListToTwoDimensionalArray3() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            list.add(i + 1);
        }
        int[][] result = new ConvertList().toArray(list, 2);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}};
        assertThat(result, is(expected));
    }
}
