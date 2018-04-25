package ru.job4j.dep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * All departments locate here.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 2.0
 * @since 23/04/2018
 */
public class Container {
    /**
     * The multiplicity of departments, built by 'DepBuilder' from input parsed words.
     */
    private final HashSet<Dep> container = new HashSet<Dep>();

    /**
     * Adds new department to container.
     *
     * @param dep
     */
    public void add(Dep dep) {
        this.container.add(dep);
    }

    /**
     * Fills this container by parsed and built-missing departments.
     *
     * @param depsArray - the array of input department strings.
     */
    public void fill(String[] depsArray) {
        Parser parser;
        Dep[] deps;
        for (String str : depsArray) {
            parser = new Parser(str);
            deps = new Dep[parser.getInput().length];
            for (int index = 0; index < parser.getInput().length; index++) {
                if (index == 0) {
                    deps[index] = new Dep(parser.getWord(index));
                } else {
                    deps[index] = DepBuilder.add(deps[index - 1], new Dep(parser.getWord(index)));
                }
                this.container.add(deps[index]);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Container{%s}", this.container);
    }

    /**
     * Sorts departments in direct order.
     *
     * @return sorted list of departments in direct order.
     */
    public List<Dep> sortInDirectOrder() {
        List<Dep> sortedDeps = new ArrayList<Dep>(this.container);
        sortedDeps.sort(new Comparator<Dep>() {
            @Override
            public int compare(Dep dep1, Dep dep2) {
                int result = 0;
                try {
                    check(dep1, dep2);
                    result = dep1.getDep().compareTo(dep2.getDep());
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
                return result;
            }
        });
        return sortedDeps;
    }

    /**
     * Sorts departments in reverse order.
     *
     * @return sorted list of departments in reverse order.
     */
    public List<Dep> sortInReverseOrder() {
        List<Dep> sortedDeps = new ArrayList<Dep>(this.container);
        sortedDeps.sort(new Comparator<Dep>() {
            @Override
            public int compare(Dep dep1, Dep dep2) {
                int result = 0;
                try {
                    check(dep1, dep2);
                    result = compareToReverse(dep1.getDep(), dep2.getDep());
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
                return result;
            }
        });
        return sortedDeps;
    }

    /**
     * Compare to strings for reverse order.
     *
     * @param firstString - the first string to be compared.
     * @param secondString - the second string to be compared.
     * @return  the value '0' if the argument string is equal to this string; a value less than '0' if this string
     *          is lexicographically less than the string argument and it is shorter; and a value greater than '0'
     *          if this string is lexicographically greater than the string argument and it is bigger.
     */
    private int compareToReverse(String firstString, String secondString) {
        int len1 = firstString.length();
        int len2 = secondString.length();
        char[] chStr1 = firstString.toCharArray();
        char[] chStr2 = secondString.toCharArray();
        int result = 0;
        int index = 0;
        while (index < Math.min(len1, len2)) {
            if (chStr1[index] != chStr2[index]) {
                result = chStr2[index] - chStr1[index];
                break;
            }
            index++;
        }
        if (result == 0) {
            result = len1 - len2;
        }
        return result;
    }

    /**
     * Checks params for null-pointer.
     *
     * @param left - left department.
     * @param right - right department.
     * @throws IllegalArgumentException if one of departments is 'null'.
     */
    private void check(Dep left, Dep right) throws IllegalArgumentException {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Invalid params. One of these departments is 'null'.");
        } else {
            if (left.getDep() == null || right.getDep() == null) {
                throw new IllegalArgumentException("Invalid params. One of these strings is 'null'.");
            }
        }
    }
}