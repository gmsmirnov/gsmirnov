package sort;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SortCountTest {

    @Test
    public void findMax() {
        int[] input1 = {1, 2, 5, 10, 7, 8};
        int[] input2 = {15, 2, 5, 10, 7, 8};
        int[] input3 = {15, 2, 5, 10, 7, 80};
        assertThat(SortCount.maxNumber(input1), is(10));
        assertThat(SortCount.maxNumber(input2), is(15));
        assertThat(SortCount.maxNumber(input3), is(80));
    }

    @Test
    public void countArrayTest() {
        int[] input = {1, 2, 5, 10, 7, 8, 2, 1, 2, 5, 1};
        int[] result = SortCount.countedArray(input);
        assertThat(result, is(new int[]{3, 3, 0 , 0, 2, 0, 1, 1, 0, 1}));
    }

    @Test
    public void sortTest() {
        int[] input = {1, 2, 5, 10, 7, 8, 2, 1, 2, 5, 1};
        int[] result = SortCount.sortCount(input);
        assertThat(result, is(new int[]{1, 1, 1, 2, 2, 2, 5, 5, 7, 8, 10}));
    }
}