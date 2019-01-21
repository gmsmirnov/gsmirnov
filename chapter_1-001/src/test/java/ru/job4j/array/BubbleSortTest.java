package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing 'Bubble sort'.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 23/01/2018
 * @version 1.0
 */
public class BubbleSortTest {
    @Test
    public void whenNotSortedArraySizeEight() {
        BubbleSort bs = new BubbleSort();
        int[] array = {5, 1, 8, 9, 9, 16, 7, 4};
        int[] expected = {1, 4, 5, 7, 8, 9, 9, 16};
        int[] result = bs.sort(array);
        assertThat(result, is(expected));
    }
}