package ru.job4j.departments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * All departments locate here.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/04/2018
 */
public class Storage {
    /**
     * The multiplicity of departments, initiates by input departments array and supplements by parser.
     */
    private final HashSet<String> depsSet;

    /**
     * Departments in direct order, fills after sorting in direct order.
     */
    private final ArrayList<String> depsDirectOrder;

    /**
     * Departments in reverse order, fills after sorting in reverse order.
     */
    private final ArrayList<String> depsRevOrder;

    /**
     * Constructs and initiates storage.
     *
     * @param departments - departments array.
     */
    public Storage(String[] departments) {
        this.depsSet = new HashSet<String>();
        this.depsSet.addAll(Arrays.asList(departments));
        this.depsDirectOrder = new ArrayList<String>();
        this.depsRevOrder = new ArrayList<String>();
    }

    /**
     * Multiplicity of departments getter.
     *
     * @return - the multiplicity of departments in HashSet form.
     */
    public HashSet<String> getDepartments() {
        return this.depsSet;
    }

    /**
     * Gets the list of departments strings in direct order.
     *
     * @return - the list of departments strings in direct order.
     */
    public ArrayList<String> getDepsDirectOrder() {
        return this.depsDirectOrder;
    }

    /**
     * Gets the list of departments strings in reverse order.
     *
     * @return - the list of departments strings in reverse order.
     */
    public ArrayList<String> getDepsRevOrder() {
        return this.depsRevOrder;
    }

    /**
     * Supplements the multiplicity of departments by parsed subdepartments.
     *
     * @param parsed - the list of parsed subdepartments.
     */
    public void addAll(List<String> parsed) {
        this.depsSet.addAll(parsed);
    }

    /**
     * Fills the list of departments strings in direct order.
     *
     * @param sorted - sorted in direct order list of departments.
     */
    public void addDirect(List<String> sorted) {
        this.depsDirectOrder.addAll(sorted);
    }

    /**
     * Fills the list of departments strings in reverse order.
     *
     * @param sorted - sorted in reverse order list of departments.
     */
    public void addReverse(List<String> sorted) {
        this.depsRevOrder.addAll(sorted);
    }
}