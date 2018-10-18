package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Simple array with generic type test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/05/2018
 */
public class SimpleArrayTest {
    private SimpleArray<Integer> simpleArray;

    private Iterator<Integer> it;

    @Before
    public void init() {
        this.simpleArray = new SimpleArray<Integer>(10);
        this.simpleArray.add(0);
        this.simpleArray.add(1);
        this.simpleArray.add(2);
        this.simpleArray.add(3);
        this.simpleArray.add(4);
        this.simpleArray.add(5);
        this.it = this.simpleArray.iterator();
    }

    @Test
    public void whenAddIntegerToContainerThenGetInteger() {
        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(5);
        int result = simpleArray.get(0);
        assertThat(result, is(5));
    }

    @Test
    public void whenAddStringToContainerThenGetString() {
        SimpleArray<String> simpleArray = new SimpleArray<String>(10);
        simpleArray.add("5");
        String result = simpleArray.get(0);
        assertThat(result, is("5"));
    }

    @Test
    public void whenAddDoubleToContainerThenGetDouble() {
        SimpleArray<Double> simpleArray = new SimpleArray<Double>(10);
        simpleArray.add(5.0);
        Double result = simpleArray.get(0);
        assertThat(result, is(5.0));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddOutOfBoundsThenArrayIndexOutOfBoundsException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(1);
        simpleArray.add(5);
        simpleArray.add(5);
    }

    @Test
    public void whenSetIntegerToContainerThenGetOldIntegerValue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(5);
        int result = simpleArray.set(0, 7);
        assertThat(result, is(5));
        assertThat(simpleArray.get(0), is(7));
    }

    @Test
    public void whenRemoveIntegerFromContainerThenGetIntegerValueAndMoveElements() {
        assertThat(this.simpleArray.remove(2), is(2));
        assertThat(this.simpleArray.get(0), is(0));
        assertThat(this.simpleArray.get(1), is(1));
        assertThat(this.simpleArray.get(2), is(3));
        assertThat(this.simpleArray.get(3), is(4));
        assertThat(this.simpleArray.get(4), is(5));
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
        Iterator<String> it = new SimpleArray<String>(10).iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenRemoveIntegerFromContainerThenGetIntegerValueAndMoveElements2() {
        assertThat(this.simpleArray.remove(3), is(3));
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
        assertThat(this.simpleArray.get(0), is(0));
        assertThat(this.simpleArray.get(1), is(1));
        assertThat(this.simpleArray.get(2), is(2));
        assertThat(this.simpleArray.get(3), is(4));
        assertThat(this.simpleArray.get(4), is(5));
    }
}