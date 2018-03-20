package ru.job4j.comparison;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sorting strings by elements.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 18/03/2018
 */
public class ListCompare implements Comparator<String> {
    /**
     * Array list for first string.
     */
    private final List<Character> leftList = new ArrayList<Character>();

    /**
     * Array list for second string.
     */
    private final List<Character> rightList = new ArrayList<Character>();

    /**
     * Comparison of two strings.
     *
     * @param left - left (first) string to compare.
     * @param right - right (second) string to compare.
     * @return positive if left string 'greater' then right string,
     * negative, if left string 'less' then right string,
     * 0 if strings are equals.
     */
    @Override
    public int compare(String left, String right) {
        this.init(left, right);
        int result = 0;
        for (int index = 0; index < leftList.size() && index < rightList.size(); index++) {
            result = Character.compare(leftList.get(index), rightList.get(index));
            if (result != 0) {
                break;
            }
        }
        if (result == 0 && leftList.size() != rightList.size()) {
            result = leftList.size() > rightList.size() ? 1 : -1;
        }
        return result;
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
     * Converts strings into array lists.
     *
     * @param left - left string.
     * @param right - right string.
     */
    private void init(String left, String right) {
        try {
            this.check(left, right);
            for (char ch : left.toCharArray()) {
                this.leftList.add(ch);
            }
            for (char ch : right.toCharArray()) {
                this.rightList.add(ch);
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }
}