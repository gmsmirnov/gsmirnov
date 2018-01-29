package ru.job4j.array;

/**
 * Searching of substring in string.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 29/01/2018
 */
public class SubstringInString {
    public boolean contains(String origin, String sub) {
        if (origin.length() < sub.length()) {
            throw new IllegalArgumentException("The substring is longer than string.");
        }
        if (sub.length() == 0) {
            throw new IllegalArgumentException("The substring is 0-length.");
        }
        boolean result = false;
        char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();
        int counter = 0;
        for (int i = 0; i < originArray.length - subArray.length + 1; i++) {
            if (result) {
                break;
            }
            for (int j = 0; j < subArray.length; j++) {
                if (originArray[j + i] == subArray[j]) {
                    counter++;
                } else {
                    counter = 0;
                    break;
                }
                if (counter == subArray.length) {
                    result = true;
                }
            }
        }
        return result;
    }
}