package ru.job4j.departments;

import java.util.*;

/**
 * Sorting departments by name in direct and reverse order.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/04/2018
 */
public class Sort {
    /**
     * The list of departments.
     */
    private final Storage departments;

    /**
     * Constructor for department sort.
     *
     * @param departments - the list of all departments.
     */
    public Sort(Storage departments) {
        this.departments = departments;
    }

    /**
     * Sorts departments in direct order.
     *
     * @return sorted list of departments in direct order.
     */
    public List<String> sortInDirectOrder() {
        List<String> sortedDepartments = new ArrayList<String>(this.departments.getDepartments());
        sortedDepartments.sort(new Comparator<String>() {
            @Override
            public int compare(String dep1, String dep2) {
                int result = 0;
                try {
                    check(dep1, dep2);
                    result = dep1.compareTo(dep2);
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
                return result;
            }
        });
        return sortedDepartments;
    }

    /**
     * Sorts departments in reverse order.
     *
     * @return sorted list of departments in reverse order.
     */
    public List<String> sortInReverseOrder() {
        List<String> preSortedDep = new ArrayList<String>(this.departments.getDepartments());
        preSortedDep.sort(new Comparator<String>() {
            @Override
            public int compare(String dep1, String dep2) {
                int result = 0;
                try {
                    check(dep1, dep2);
                    result = compareToReverse(dep1, dep2);
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
                return result;
            }
        });
        return reverseList(preSortedDep);
    }

    /**
     * Checks params for null-pointer.
     *
     * @param left - left string.
     * @param right - right string.
     * @throws IllegalArgumentException if one of strings in 'null'.
     */
    private void check(String left, String right) throws IllegalArgumentException {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Invalid params. One of these strings is 'null'.");
        }
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
                result = chStr1[index] - chStr2[index];
                break;
            }
            index++;
        }
        if (result == 0) {
            result = len2 - len1;
        }
        return result;
    }

    /**
     * Revers the list of strings.
     *
     * @param list - the list of strings.
     * @return reversed list of strings.
     */
    private List<String> reverseList(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (int index = list.size() - 1; index >= 0; index--) {
            resultList.add(list.get(index));
        }
        return resultList;
    }
}