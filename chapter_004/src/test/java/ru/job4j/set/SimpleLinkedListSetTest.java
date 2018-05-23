package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.BaseList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Simple set based on linked list with generic type test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/05/2018
 */
public class SimpleLinkedListSetTest {
    private BaseList<Integer> intSet;

    private Iterator<Integer> it;

    @Before
    public void init() {
        this.intSet = new SimpleLinkedListSet<Integer>();
        for (int i = 0; i < 5; i++) {
            this.intSet.add(i);
        }
        this.it = this.intSet.iterator();
    }

    @Test
    public void whenAddNotExistingElementThanTrueElseFalse() {
        assertThat(this.intSet.add(4), is(false));
        assertThat(this.intSet.add(5), is(true));
        assertThat(this.intSet.add(5), is(false));
        assertThat(this.intSet.add(6), is(true));
        assertThat(this.intSet.add(6), is(false));
    }

    @Test
    public void whenHasNextThenNext() {
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
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenListAddsNewElementsThenIteratorThrowsException() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(0));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        this.intSet.add(6);
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
    }
}