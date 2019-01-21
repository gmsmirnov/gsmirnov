package ru.job4j.dep;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests container sorting.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 2.0
 * @since 23/04/2018
 */
public class ContainerTest {
    private final String[] depsArray = {"K2\\SK1\\SSK1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
            "K2", "K2\\SK1\\SSK2", "K1\\SK2"};

    private final ArrayList<Dep> directOrder = new ArrayList<Dep>();

    private final ArrayList<Dep> reverseOrder = new ArrayList<Dep>();

    @Before
    public void init() {
        this.directOrder.add(new Dep("K1"));
        this.directOrder.add(new Dep("K1\\SK1"));
        this.directOrder.add(new Dep("K1\\SK1\\SSK1"));
        this.directOrder.add(new Dep("K1\\SK1\\SSK2"));
        this.directOrder.add(new Dep("K1\\SK2"));
        this.directOrder.add(new Dep("K2"));
        this.directOrder.add(new Dep("K2\\SK1"));
        this.directOrder.add(new Dep("K2\\SK1\\SSK1"));
        this.directOrder.add(new Dep("K2\\SK1\\SSK2"));

        this.reverseOrder.add(new Dep("K2"));
        this.reverseOrder.add(new Dep("K2\\SK1"));
        this.reverseOrder.add(new Dep("K2\\SK1\\SSK2"));
        this.reverseOrder.add(new Dep("K2\\SK1\\SSK1"));
        this.reverseOrder.add(new Dep("K1"));
        this.reverseOrder.add(new Dep("K1\\SK2"));
        this.reverseOrder.add(new Dep("K1\\SK1"));
        this.reverseOrder.add(new Dep("K1\\SK1\\SSK2"));
        this.reverseOrder.add(new Dep("K1\\SK1\\SSK1"));
    }

    @Test
    public void whenSortInDirectOrder() {
        Container cont = new Container();
        cont.fill(this.depsArray);
        System.out.println(cont.sortInDirectOrder());
        assertThat(cont.sortInDirectOrder(), is(this.directOrder));
    }

    @Test
    public void whenSortInReverseOrder() {
        Container cont = new Container();
        cont.fill(this.depsArray);
        System.out.println(cont.sortInReverseOrder());
        assertThat(cont.sortInReverseOrder(), is(this.reverseOrder));
    }
}