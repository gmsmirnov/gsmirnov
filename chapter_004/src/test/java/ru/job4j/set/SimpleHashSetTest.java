package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {
    private SimpleHashSet<String> storage = new SimpleHashSet<String>();

    private SimpleHashSet<Integer> intStorage = new SimpleHashSet<Integer>();

    @Before
    public void init() {
        this.storage.add("Hi!");
        this.storage.add("Bye!");
        for (int i = 0; i < 1000; i++) {
            this.intStorage.add(i * 2);
        }
    }

    @Test
    public void whenElementWasRemovedThanContainsFalse() {
        assertThat(this.intStorage.contains(1900), is(true));
        assertThat(this.intStorage.remove(1900), is(true));
        assertThat(this.intStorage.contains(1900), is(false));
    }

    @Test
    public void whenHasNextStringThanNext() {
        Iterator<String> iterStr = this.storage.iterator();
        assertThat(iterStr.hasNext(), is(true));
        assertThat(iterStr.next(), is("Bye!"));
        assertThat(iterStr.hasNext(), is(true));
        assertThat(iterStr.next(), is("Hi!"));
        assertThat(iterStr.hasNext(), is(false));
    }

    @Test
    public void whenHasNextIntegerThanTrue() {
        Iterator<Integer> iterInt = this.intStorage.iterator();
        assertThat(iterInt.hasNext(), is(true));
    }

    @Test
    public void print() {
        System.out.println(this.intStorage);
        System.out.println(String.format("The size of container (quantity of elements in container) is: %s", this.intStorage.size()));
        System.out.println(String.format("The length of bucket-array is: %s", this.intStorage.getLength()));
        System.out.println(String.format("The load of container is: %s", this.intStorage.getLoad()));
    }

    @Test
    public void whenClearThanContainerIsEmpty() {
        this.intStorage.clear();
        assertThat(this.intStorage.size(), is(0));
        Iterator<Integer> iterInt = this.intStorage.iterator();
        assertThat(iterInt.hasNext(), is(false));
    }

    @Test
    public void whenAddNewElementThanContainsIsTrue() {
        assertThat(this.intStorage.contains(2000), is(false));
        this.intStorage.add(2000);
        assertThat(this.intStorage.contains(2000), is(true));
        assertThat(this.intStorage.size(), is(1001));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenContainerIsEmptyThanNextBringException() {
        this.intStorage.clear();
        Iterator<Integer> iterInt = this.intStorage.iterator();
        iterInt.next();
    }

    @Test
    public void iteratorPrint() {
        Iterator<Integer> iterInt = this.intStorage.iterator();
        while (iterInt.hasNext()) {
            System.out.println(iterInt.next());
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAfterIteratorInitModifyThanException() {
        Iterator<Integer> iterInt = this.intStorage.iterator();
        this.intStorage.add(2000);
        iterInt.next();
    }

    @Test
    public void whenContainerIsEmptyThanIsEmptyTrue() {
        this.intStorage.clear();
        assertThat(this.intStorage.isEmpty(), is(true));
    }

    @Test
    public void negativeTest() {
        this.intStorage.clear();
        for (int i = 0; i > -1000; i--) {
            this.intStorage.add(i * 2);
        }
        for (int i = 0; i > -1000; i--) {
            this.intStorage.add(i * 3);
        }
        Iterator<Integer> iterInt = this.intStorage.iterator();
        while (iterInt.hasNext()) {
            System.out.println(iterInt.next());
        }
        System.out.println(this.intStorage.size());
        System.out.println(this.intStorage.getLoad());
        System.out.println(this.intStorage.getLength());
        assertThat(this.intStorage.contains(-1998), is(true));
        assertThat(this.intStorage.remove(-1998), is(true));
        assertThat(this.intStorage.contains(-1998), is(false));
        assertThat(this.intStorage.contains(-2550), is(true));
        assertThat(this.intStorage.remove(-2550), is(true));
        assertThat(this.intStorage.contains(-2550), is(false));
        assertThat(this.intStorage.size(), is(1664));
    }
}