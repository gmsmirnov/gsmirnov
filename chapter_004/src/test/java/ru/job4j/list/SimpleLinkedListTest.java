package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple linked list with generic type test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 18/05/2018
 */
public class SimpleLinkedListTest {
    private BaseList<Integer> intList;

    private Iterator<Integer> it;

    @Before
    public void init() {
        this.intList = new SimpleLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.intList.add(i);
        }
        this.it = this.intList.iterator();
    }

    @Test
    public void whenGetIntegerFromListThenTrue() {
        for (int i = 0; i < 5; i++) {
            assertThat(this.intList.get(i), is(i));
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBoundsThenArrayIndexOutOfBoundsException() {
        assertThat(this.intList.get(10), is(10));
    }

    @Test
    public void whenSetNewValueThenReturnOldValue() {
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.set(0, 10), is(0));
        assertThat(this.intList.get(0), is(10));
    }

    @Test
    public void whenRemoveThenReturnOldValue() {
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.get(1), is(1));
        assertThat(this.intList.get(2), is(2));
        assertThat(this.intList.remove(1), is(1));
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.get(1), is(2));
        assertThat(this.intList.get(2), is(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorWalksThenIteratorGetsAllElementsWhileThereAreAnyElements() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(4));
        assertThat(this.it.hasNext(), is(false));
        this.it.next();
    }

    @Test
    public void whenListIsEmptyIteratorReturnsFalse() {
        Iterator<String> it = new SimpleLinkedList<String>().iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddStringToContainerThenGetString() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("5");
        String result = simpleLinkedList.get(0);
        assertThat(result, is("5"));
    }

    @Test
    public void whenAddDoubleToContainerThenGetDouble() {
        SimpleLinkedList<Double> simpleLinkedList = new SimpleLinkedList<Double>();
        simpleLinkedList.add(5.0);
        Double result = simpleLinkedList.get(0);
        assertThat(result, is(5.0));
    }

    @Test
    public void whenRemoveValueFromContainerThenGetThatValueAndMoveElements() {
        assertThat(this.intList.remove(2), is(2));
        this.it = this.intList.iterator();
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(4));
        assertThat(this.it.hasNext(), is(false));
        assertThat(this.intList.get(0), is(0));
        assertThat(this.intList.get(1), is(1));
        assertThat(this.intList.get(2), is(3));
        assertThat(this.intList.get(3), is(4));
    }

    @Test
    public void whenAddValuesMoreThanContainerSizeThenContainerGrows() {
        assertThat(this.intList.size(), is(5));
        this.intList.add(5);
        this.intList.add(6);
        this.intList.add(7);
        this.intList.add(8);
        this.intList.add(9);
        this.intList.add(10);
        this.intList.add(11);
        this.it = this.intList.iterator();
        assertThat(this.intList.size(), is(12));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(4));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(5));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(6));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(7));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(8));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(9));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(10));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(11));
        assertThat(this.it.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenListAddsNewElementsThenIteratorThrowsException() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        this.intList.add(6);
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenListRemovesAnElementsThenIteratorThrowsException() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        this.intList.remove(4);
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
    }

    @Test
    public void whenAdds150ElementsThenContainerGrowsFor150() {
        for (int i = 0; i < 150; i++) {
            this.intList.add(i);
        }
        assertThat(this.intList.size(), is(155));
        assertThat(this.intList.get(154), is(149));
    }

    @Test
    public void whenAddIntegerToSpecifiedPositionThenRearrangeList() {
        this.intList.add(4, 10);
        assertThat(this.intList.get(3), is(3));
        assertThat(this.intList.get(4), is(10));
        assertThat(this.intList.get(5), is(4));
    }

    @Test
    public void whenTheListContainsElementThenTrueElseFalse() {
        assertThat(this.intList.contains(0), is(true));
        assertThat(this.intList.contains(1), is(true));
        assertThat(this.intList.contains(2), is(true));
        assertThat(this.intList.contains(3), is(true));
        assertThat(this.intList.contains(4), is(true));
        assertThat(this.intList.contains(10), is(false));
    }

    @Test
    public void whenTheListContainsElementThenCheckIndex() {
        assertThat(this.intList.indexOf(0), is(0));
        assertThat(this.intList.indexOf(1), is(1));
        assertThat(this.intList.indexOf(2), is(2));
        assertThat(this.intList.indexOf(3), is(3));
        assertThat(this.intList.indexOf(4), is(4));
        assertThat(this.intList.indexOf(5), is(-1));
    }

    @Test
    public void print() {
        System.out.println(this.intList);
    }
}