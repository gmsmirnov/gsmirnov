package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleHashMapTest {
    SimpleHashMap<String, Integer> shm;

    Iterator<Entry<String, Integer>> it;

    @Before
    public void init() {
        this.shm = new SimpleHashMap<String, Integer>();
        for (int i = 0; i < 1000; i++) {
            this.shm.put("Number " + i, i);
        }
        this.it = this.shm.iterator();
    }

    @Test
    public void whenValueAddedThanGetValueByKeyIsTrue() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<String, Integer>();
        shm.put("First", 1);
        assertThat(shm.get("First"), is(1));
    }

    @Test
    public void whenValueReplacedThanReturnOldValueIsTrue() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<String, Integer>();
        shm.put("First", 1);
        assertThat(shm.put("First", 2), is(1));
        assertThat(shm.get("First"), is(2));
    }

    @Test
    public void whenRemoveValueThanReturnOldValueIsTrue() {
        SimpleHashMap<String, Integer> shm = new SimpleHashMap<String, Integer>();
        shm.put("First", 1);
        assertThat(shm.size(), is(1));
        assertThat(shm.remove("First"), is(1));
        assertThat(shm.get("First"), is((Integer) null));
        assertThat(shm.size(), is(0));
        assertThat(shm.isEmpty(), is(true));
        shm.put("First", 1);
        shm.put("First", 4);
        assertThat(shm.get("First"), is(4));
        assertThat(shm.size(), is(1));
        assertThat(shm.isEmpty(), is(false));
    }

    @Test
    public void whenAddValueThenIteratorHasNextIsTrue() {
        assertThat(this.it.hasNext(), is(true));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenMapIsClearThenSizeIsNull() {
        this.shm.clear();
        assertThat(this.shm.size(), is(0));
        assertThat(this.shm.isEmpty(), is(true));
        this.it = this.shm.iterator();
        assertThat(this.it.hasNext(), is(false));
        assertThat(this.it.next(), is((Integer) null));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenMapIsClearThenIteratorGainsConcurrentModificationException() {
        this.shm.clear();
        assertThat(this.it.hasNext(), is(false));
        assertThat(this.it.next(), is(false));
    }

    @Test
    public void print() {
        System.out.println(this.shm);
    }

    @Test
    public void whenDeletePairFromCollisionListThanMapHasNoThatPair() {
        assertThat(this.shm.remove("Number 964"), is(964));
        assertThat(this.shm.get("Number 964"), is((Integer) null));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutsNewPairThanIteratorGetsConcurrentModificationException() {
        this.shm.put("New pair", -1);
        this.it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemovePairThanIteratorGetsConcurrentModificationException() {
        this.shm.remove("Number 1");
        this.it.next();
    }

    @Test
    public void whenRemoveNoPairThanIteratorGetsNext() {
        this.shm.remove("Number -1");
        assertThat(this.it.next().getValue(), is(469));
    }
}