package ru.job4j.departments;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests sorting.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/04/2018
 */
public class SortTest {
    private String[] depsArray = {"K2\\SK1\\SSK1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
            "K2", "K2\\SK1\\SSK2", "K1\\SK2", "K1", "K2\\SK1"};

    private Storage departments = new Storage(depsArray);

    private ArrayList<String> directOrder = new ArrayList<String>();

    private ArrayList<String> reverseOrder = new ArrayList<String>();

    @Before
    public void init() {
        this.directOrder.add("K1");
        this.directOrder.add("K1\\SK1");
        this.directOrder.add("K1\\SK1\\SSK1");
        this.directOrder.add("K1\\SK1\\SSK2");
        this.directOrder.add("K1\\SK2");
        this.directOrder.add("K2");
        this.directOrder.add("K2\\SK1");
        this.directOrder.add("K2\\SK1\\SSK1");
        this.directOrder.add("K2\\SK1\\SSK2");

        this.reverseOrder.add("K2");
        this.reverseOrder.add("K2\\SK1");
        this.reverseOrder.add("K2\\SK1\\SSK2");
        this.reverseOrder.add("K2\\SK1\\SSK1");
        this.reverseOrder.add("K1");
        this.reverseOrder.add("K1\\SK2");
        this.reverseOrder.add("K1\\SK1");
        this.reverseOrder.add("K1\\SK1\\SSK2");
        this.reverseOrder.add("K1\\SK1\\SSK1");
    }

    @Test
    public void whenSortInDirectOrder() {
        Sort ds = new Sort(this.departments);
        System.out.println(ds.sortInDirectOrder());
        assertThat(ds.sortInDirectOrder(), is(this.directOrder));
    }

    @Test
    public void whenSortInReverseOrder() {
        Sort ds = new Sort(this.departments);
        System.out.println(ds.sortInReverseOrder());
        assertThat(ds.sortInReverseOrder(), is(this.reverseOrder));
    }
}