package ru.job4j.array;

/**
 * Square array.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 21/01/2018
 * @version 1.0
 */
public class Square {
    /**
     * Filling an array of squared numbers.
     * @param bound - the size of array and the maximum squared number.
     * @return array of squared numbers.
     */
    public int[] calculate(int bound) {
        if (bound < 0) {
            throw new IllegalArgumentException("bound must be > 0");
        }
        int[] rst = new int[bound];
        for (int i = 1; i <= rst.length; i++) {
            rst[i - 1] = (int) Math.pow(i, 2.0);
        }
        return rst;
    }
}