package ru.job4j.comparison;

import java.util.Comparator;

/**
 * Sorting strings by elements.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 18/03/2018
 */
public class ListCompare implements Comparator<String> {
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
        int result = 0;
        try {
            this.check(left, right);
            for (int index = 0; index < left.length() && index < right.length(); index++) {
                result = Character.compare(left.charAt(index), right.charAt(index));
                if (result != 0) {
                    break;
                }
            }
            if (result == 0 && left.length() != right.length()) {
                result = left.length() > right.length() ? 1 : -1;
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
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
}