package ru.job4j.array;

/**
 * Checks if the string begins with prefix.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 04/07/2018
 * @version 1.0
 */
public class ArrayChar {
    /**
     * The char array.
     */
    private final char[] data;

    /**
     * The constructor.
     *
     * @param line - the specified line, converted into char array.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Checks if this char array starts with the specified string.
     *
     * @param prefix - the specified string.
     * @return true if this char array starts with the specified string.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] pref = prefix.toCharArray();
        if (this.data.length < pref.length) {
            result = false;
        } else {
            for (int index = 0; index < pref.length; index++) {
                if (this.data[index] != pref[index]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}