package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Tests the removal duplicated words from array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 24/01/2018
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] array = {"Hi", "World", "Perfect", "Perfect", "New", "Hi", "World", "Perfect", "World", "Perfect"};
        String[] expected = {"Hi", "World", "Perfect", "New"};
        String[] result = ad.remove(array);
        assertThat(result, arrayContainingInAnyOrder(expected));
    }
}