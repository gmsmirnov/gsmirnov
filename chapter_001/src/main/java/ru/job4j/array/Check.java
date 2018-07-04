package ru.job4j.array;

/**
 * Checks if all booleans in the array are the same.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 04/07/2018
 * @version 1.0
 */
public class Check {
    /**
     * Checks if all booleans in the array are the same.
     *
     * @param data the specified array.
     * @return true if all booleans in the specified array are true or all booleans are false.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        if (data.length == 0) {
            result = false;
        } else {
            for (int index = 0; index < data.length - 1; index++) {
                if (data[index] != data[index + 1]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}