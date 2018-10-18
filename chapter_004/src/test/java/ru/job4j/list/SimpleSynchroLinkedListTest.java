package ru.job4j.list;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Simple synchronized linked list test.
 * The first part is a single thread test.
 * The second part is a multi thread test.
 */
public class SimpleSynchroLinkedListTest {
    // The beginning of single thread test
    @Test
    public void whenGetIntegerFromListThenTrue() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        for (int i = 0; i < 5; i++) {
            assertThat(intList.get(i), is(i));
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBoundsThenArrayIndexOutOfBoundsException() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        assertThat(intList.get(10), is(10));
    }

    @Test
    public void whenSetNewValueThenReturnOldValue() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        assertThat(intList.get(0), is(0));
        assertThat(intList.set(0, 10), is(0));
        assertThat(intList.get(0), is(10));
    }

    @Test
    public void whenRemoveThenReturnOldValue() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        assertThat(intList.get(0), is(0));
        assertThat(intList.get(1), is(1));
        assertThat(intList.get(2), is(2));
        assertThat(intList.remove(1), is(1));
        assertThat(intList.get(0), is(0));
        assertThat(intList.get(1), is(2));
        assertThat(intList.get(2), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorWalksThenIteratorGetsAllElementsWhileThereAreAnyElements() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        Iterator<Integer> it = intList.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void whenListIsEmptyIteratorReturnsFalse() {
        Iterator<String> it = new SimpleSynchroLinkedList<String>().iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddStringToContainerThenGetString() {
        SimpleSynchroLinkedList<String> simpleArray = new SimpleSynchroLinkedList<String>();
        simpleArray.add("5");
        String result = simpleArray.get(0);
        assertThat(result, is("5"));
    }

    @Test
    public void whenAddDoubleToContainerThenGetDouble() {
        SimpleSynchroLinkedList<Double> simpleArray = new SimpleSynchroLinkedList<Double>();
        simpleArray.add(5.0);
        Double result = simpleArray.get(0);
        assertThat(result, is(5.0));
    }

    @Test
    public void whenRemoveValueFromContainerThenGetThatValueAndMoveElements() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        assertThat(intList.remove(2), is(2));
        Iterator<Integer> it = intList.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        assertThat(intList.get(0), is(0));
        assertThat(intList.get(1), is(1));
        assertThat(intList.get(2), is(3));
        assertThat(intList.get(3), is(4));
    }

    @Test
    public void whenAddValuesMoreThanContainerSizeThenContainerGrows() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        assertThat(intList.size(), is(5));
        intList.add(5);
        intList.add(6);
        intList.add(7);
        intList.add(8);
        intList.add(9);
        intList.add(10);
        intList.add(11);
        Iterator<Integer> it = intList.iterator();
        assertThat(intList.size(), is(12));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(11));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAdds150ElementsThenContainerGrowsFor150() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        IntStream.range(0, 150).forEach(intList::add);
        assertThat(intList.size(), is(155));
        assertThat(intList.get(154), is(149));
    }

    @Test
    public void whenAddIntegerToSpecifiedPositionThenRearrangeList() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        intList.add(4, 10);
        assertThat(intList.get(3), is(3));
        assertThat(intList.get(4), is(10));
        assertThat(intList.get(5), is(4));
    }

    @Test
    public void whenTheListContainsElementThenTrueElseFalse() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        assertThat(intList.contains(0), is(true));
        assertThat(intList.contains(1), is(true));
        assertThat(intList.contains(2), is(true));
        assertThat(intList.contains(3), is(true));
        assertThat(intList.contains(4), is(true));
        assertThat(intList.contains(10), is(false));
    }

    @Test
    public void whenTheListContainsElementThenCheckIndex() {
        SimpleSynchroLinkedList<Integer> intList = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 5).forEach(intList::add);
        assertThat(intList.indexOf(0), is(0));
        assertThat(intList.indexOf(1), is(1));
        assertThat(intList.indexOf(2), is(2));
        assertThat(intList.indexOf(3), is(3));
        assertThat(intList.indexOf(4), is(4));
        assertThat(intList.indexOf(5), is(-1));
    }
    // The end of single thread test

    // The beginning of multi-thread test.
    @Test
    public void whenAddToLinkedList() throws InterruptedException {
        SimpleSynchroLinkedList<Integer> list = new SimpleSynchroLinkedList<Integer>();
        Thread first = new Thread(() -> IntStream.range(0, 5).forEach(list::add));
        Thread second = new Thread(() -> IntStream.range(5, 10).forEach(list::add));
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list.size(), is(10));
        assertThat(list.toArray(), arrayContainingInAnyOrder(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void whenRemoveFromLinkedList() throws InterruptedException {
        SimpleSynchroLinkedList<Integer> list = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 50).forEach(list::add);
        Thread first = new Thread(() -> {
            for (int index = 0; index < 10; index++) {
                list.remove(index);
            }
        });
        Thread second = new Thread(() -> {
            for (int index = 0; index < 10; index++) {
                list.remove(index);
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list.size(), is(30));
        System.out.println(list);
    }

    @Test
    public void whenSetToLinkedList() throws InterruptedException {
        SimpleSynchroLinkedList<Integer> list = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 100).forEach(list::add);
        Thread first = new Thread(() -> {
            for (int index = 0; index < 50; index++) {
                list.set(index, 100 + index);
            }
        });
        Thread second = new Thread(() -> {
            for (int index = 50; index < 100; index++) {
                list.set(index, 200 + index);
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list.size(), is(100));
        List<Integer> expected = new ArrayList<Integer>();
        IntStream.range(100, 150).forEach(expected::add);
        IntStream.range(250, 300).forEach(expected::add);
        assertThat(list.toArray(), arrayContainingInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenContainsInLinkedList() throws InterruptedException {
        SimpleSynchroLinkedList<Integer> list = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 100).forEach(list::add);
        Thread first = new Thread(() -> {
            for (int index = 0; index < 50; index++) {
                assertThat(list.contains(index), is(true));
            }
        });
        Thread second = new Thread(() -> {
            for (int index = 50; index < 100; index++) {
                assertThat(list.contains(index), is(true));
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    public void whenIndexOfInLinkedList() throws InterruptedException {
        SimpleSynchroLinkedList<Integer> list = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 100).forEach(list::add);
        Thread first = new Thread(() -> {
            for (int index = 0; index < 50; index++) {
                assertThat(list.indexOf(index), is(index));
            }
        });
        Thread second = new Thread(() -> {
            for (int index = 50; index < 100; index++) {
                assertThat(list.indexOf(index), is(index));
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetFailSafeIteratorFromLinkedList() throws InterruptedException {
        SimpleSynchroLinkedList<Integer> list = new SimpleSynchroLinkedList<Integer>();
        IntStream.range(0, 100).forEach(list::add);
        Thread first = new Thread(() -> {
            Iterator<Integer> it = list.iterator();
            for (int index = 0; index < 100; index++) {
                assertThat(it.hasNext(), is(true));
                assertThat(it.next(), is(index));
            }
        });
        Thread second = new Thread(() ->
                IntStream.range(100, 200).forEach(list::add)
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list.size(), is(200));
        List<Integer> expected = new ArrayList<Integer>();
        IntStream.range(0, 200).forEach(expected::add);
        assertThat(list.toArray(), arrayContainingInAnyOrder(expected.toArray()));
        Iterator<Integer> it = list.iterator();
        for (int index = 0; index < 200; index++) {
            assertThat(it.hasNext(), is(true));
            assertThat(it.next(), is(index));
        }
        assertThat(it.hasNext(), is(false));
        assertThat(it.next(), is(200));
    }
    // The end of multi-thread test.
}