package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple array list with generic type test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/05/2018
 */
public class SimpleArrayListTest {
    private SimpleArrayList<Integer> simpleArrayList;

    private Iterator<Integer> it;

    @Before
    public void init() {
        this.simpleArrayList = new SimpleArrayList<Integer>(10);
        this.simpleArrayList.add(0);
        this.simpleArrayList.add(1);
        this.simpleArrayList.add(2);
        this.simpleArrayList.add(3);
        this.simpleArrayList.add(4);
        this.simpleArrayList.add(5);
        this.it = this.simpleArrayList.iterator();
    }

    @Test
    public void whenAddIntegerToContainerThenGetInteger() {
        SimpleArrayList<Integer> simpleArray = new SimpleArrayList<Integer>(10);
        simpleArray.add(5);
        int result = simpleArray.get(0);
        assertThat(result, is(5));
    }

    @Test
    public void whenAddStringToContainerThenGetString() {
        SimpleArrayList<String> simpleArray = new SimpleArrayList<String>(10);
        simpleArray.add("5");
        String result = simpleArray.get(0);
        assertThat(result, is("5"));
    }

    @Test
    public void whenAddDoubleToContainerThenGetDouble() {
        SimpleArrayList<Double> simpleArray = new SimpleArrayList<Double>(10);
        simpleArray.add(5.0);
        Double result = simpleArray.get(0);
        assertThat(result, is(5.0));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddOutOfBoundsThenArrayIndexOutOfBoundsException() {
        SimpleArrayList<Integer> simpleArray = new SimpleArrayList<Integer>(1);
        simpleArray.add(5);
        simpleArray.add(5);
    }

    @Test
    public void whenSetIntegerToContainerThenGetOldIntegerValue() {
        SimpleArrayList<Integer> simpleArray = new SimpleArrayList<Integer>(10);
        simpleArray.add(5);
        int result = simpleArray.set(0, 7);
        assertThat(result, is(5));
        assertThat(simpleArray.get(0), is(7));
    }

    @Test
    public void whenRemoveIntegerFromContainerThenGetIntegerValueAndMoveElements() {
        assertThat(this.simpleArrayList.remove(2), is(2));
        assertThat(this.simpleArrayList.get(0), is(0));
        assertThat(this.simpleArrayList.get(1), is(1));
        assertThat(this.simpleArrayList.get(2), is(3));
        assertThat(this.simpleArrayList.get(3), is(4));
        assertThat(this.simpleArrayList.get(4), is(5));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorTestsThenIteratorGetsAllElementsWhileThereAreAnyElements() {
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
        assertThat(this.it.hasNext(), is(false));
        this.it.next();
    }

    @Test
    public void  whenSimpleArrayIsEmptyIteratorReturnsFalse() {
        Iterator<String> it = new SimpleArrayList<String>(10).iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenRemoveIntegerFromContainerThenGetIntegerValueAndMoveElements2() {
        assertThat(this.simpleArrayList.remove(3), is(3));
        this.it = this.simpleArrayList.iterator();
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(4));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(5));
        assertThat(this.it.hasNext(), is(false));
        assertThat(this.simpleArrayList.get(0), is(0));
        assertThat(this.simpleArrayList.get(1), is(1));
        assertThat(this.simpleArrayList.get(2), is(2));
        assertThat(this.simpleArrayList.get(3), is(4));
        assertThat(this.simpleArrayList.get(4), is(5));
    }

    @Test
    public void whenAddIntegerToContainerMoreThanContainerSizeThenContainerGrows() {
        assertThat(this.simpleArrayList.getActualSize(), is(6));
        this.simpleArrayList.add(6);
        this.simpleArrayList.add(7);
        this.simpleArrayList.add(8);
        this.simpleArrayList.add(9);
        this.simpleArrayList.add(10);
        this.simpleArrayList.add(11);
        this.it = this.simpleArrayList.iterator();
        assertThat(this.simpleArrayList.getActualSize(), is(12));
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
        this.simpleArrayList.add(6);
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenListRemovesAnElementsThenIteratorThrowsException() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        this.simpleArrayList.remove(4);
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
    }

    @Test
    public void whenAdds150ElementsThenContainerGrowsFor150() {
        for (int i = 0; i < 150; i++) {
            this.simpleArrayList.add(i);
        }
        assertThat(this.simpleArrayList.getActualSize(), is(156));
        assertThat(this.simpleArrayList.get(155), is(149));
    }
}