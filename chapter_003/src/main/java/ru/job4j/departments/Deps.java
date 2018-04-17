package ru.job4j.departments;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class, which creates departments storage as HashSet, fill it, and initiate parsing and sorting.
 * Sorted departments are placed into storage in DirectOrderList and ReverseOrderList.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/04/2018
 */
public class Deps {
    /**
     * The departments storage, which contains initial departments, parsed and sorted departments.
     */
    private final Storage departments;

    /**
     * Creates and initiates storage by String array.
     *
     * @param departments - String array (input list of departments in array form).
     */
    public Deps(String[] departments) {
        this.departments = new Storage(departments);
    }

    /**
     * Parses all departments and puts them into storage.
     */
    public void parseAll() {
        List<String> temp = new ArrayList<String>();
        for (String department : departments.getDepartments()) {
            Parser parser = new Parser(department);
            parser.parse();
            temp.addAll(parser.getSubdepartments());
        }
        this.departments.addAll(temp);
    }

    /**
     * Sorts all departments in direct and reverse order and put them into storage.
     */
    public void sortAll() {
        Sort sort = new Sort(this.departments);
        departments.addDirect(sort.sortInDirectOrder());
        departments.addReverse(sort.sortInReverseOrder());
    }

    /**
     * Departments getter.
     *
     * @return storage with all containers.
     */
    public Storage getDepartments() {
        return this.departments;
    }
}