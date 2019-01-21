package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing 'CombinationOfTwoSortedArraysToOne'.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 02/02/2018
 * @version 1.2
 */
public class CombinationOfTwoSortedArraysToOneTest {
    @Test
    public void whenTwoSortedArraysThenOneSortedArray() {
        CombinationOfTwoSortedArraysToOne combo = new CombinationOfTwoSortedArraysToOne();
        int[] firstArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] secondArray = {4, 6, 8, 10};
        int[] result = combo.arraysCombination2(firstArray, secondArray);
        int[] expected = {1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 10};
        assertThat(result, is(expected));
    }

    @Test
    public void whenTwoSortedArraysThenOneSortedArray2() {
        CombinationOfTwoSortedArraysToOne combo = new CombinationOfTwoSortedArraysToOne();
        int[] firstArray = {4, 6, 8, 10};
        int[] secondArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result = combo.arraysCombination2(firstArray, secondArray);
        int[] expected = {1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 10};
        assertThat(result, is(expected));
    }

    @Test
    public void whenTwoSortedArraysThenOneSortedArray3() {
        CombinationOfTwoSortedArraysToOne combo = new CombinationOfTwoSortedArraysToOne();
        int[] firstArray = {4, 6, 8, 10};
        int[] secondArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] result = combo.arraysCombination3(firstArray, secondArray);
        int[] expected = {1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 10};
        assertThat(result, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOneOfArraysIsEmptyThanException() {
        new CombinationOfTwoSortedArraysToOne().arraysCombination2(null, new int[] {1, 2, 1});
    }
}
