package ru.job4j.departments;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;

/**
 * Tests full logic of departments parsing and sorting.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/04/2018
 */
public class DepsTest {
    private String[] depsArray = {"A2\\SA1\\SSA1", "A1\\SA1", "A1\\SA1\\SSA1", "A1\\SA1\\SSA2",
            "A2", "A2\\SA1\\SSA2", "A1\\SA2"};

    private ArrayList<String> directOrder = new ArrayList<String>();

    private ArrayList<String> reverseOrder = new ArrayList<String>();

    @Before
    public void init() {
        this.directOrder.add("A1");
        this.directOrder.add("A1\\SA1");
        this.directOrder.add("A1\\SA1\\SSA1");
        this.directOrder.add("A1\\SA1\\SSA2");
        this.directOrder.add("A1\\SA2");
        this.directOrder.add("A2");
        this.directOrder.add("A2\\SA1");
        this.directOrder.add("A2\\SA1\\SSA1");
        this.directOrder.add("A2\\SA1\\SSA2");

        this.reverseOrder.add("A2");
        this.reverseOrder.add("A2\\SA1");
        this.reverseOrder.add("A2\\SA1\\SSA2");
        this.reverseOrder.add("A2\\SA1\\SSA1");
        this.reverseOrder.add("A1");
        this.reverseOrder.add("A1\\SA2");
        this.reverseOrder.add("A1\\SA1");
        this.reverseOrder.add("A1\\SA1\\SSA2");
        this.reverseOrder.add("A1\\SA1\\SSA1");
    }

    @Test
    public void whenStoreDepartmentsThenStorageContainsAllDepartmentsInDirectOrder() {
        Deps deps = new Deps(this.depsArray);
        deps.parseAll();
        deps.sortAll();
        assertThat(deps.getDepartments().getDepsDirectOrder(), is(this.directOrder));
    }

    @Test
    public void whenStoreDepartmentsThenStorageContainsAllDepartmentsInReverseOrder() {
        Deps deps = new Deps(this.depsArray);
        deps.parseAll();
        deps.sortAll();
        assertThat(deps.getDepartments().getDepsRevOrder(), is(this.reverseOrder));
    }
}