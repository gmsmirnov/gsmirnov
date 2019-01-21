package ru.job4j.array;

/**
 * Turning array.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 22/01/2018
 * @version 1.0
 */
public class Turn {
    /**
     * Turning array.
     * @param data - the array which turns.
     * @return the turned array.
     */
    public int[] back(int[] data) {
        int tmp;
        for (int index = 0; index < data.length / 2; index++) {
            tmp = data[index];
            data[index] = data[data.length - index - 1];
            data[data.length - index - 1] = tmp;
        }
        return data;
    }
}