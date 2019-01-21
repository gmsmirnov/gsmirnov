package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleListTest {
    private SimpleList<Integer> list;

    @Before
    public void beforeTest() {
        this.list = new SimpleList<Integer>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
    }

    @Test
    public void whenAddElementsThanCanGetElement() {
        assertThat(this.list.get(1), is(2));
    }

    @Test
    public void whenSizeIsThreeThanGetSizeReturnsThree() {
        assertThat(this.list.getSize(), is(3));
    }

    @Test
    public void whenDeleteThanListReturnsFirstElementAndGetsSmaller() {
        assertThat(this.list.get(0), is(3));
        assertThat(this.list.delete(), is(3));
        assertThat(this.list.get(0), is(2));
        assertThat(this.list.delete(), is(2));
        assertThat(this.list.getSize(), is(1));
        assertThat(this.list.get(0), is(1));
        assertThat(this.list.delete(), is(1));
        assertThat(this.list.getSize(), is(0));
    }
}