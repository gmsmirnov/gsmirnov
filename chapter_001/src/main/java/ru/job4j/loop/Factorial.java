package ru.job4j.loop;

/**
 * Factorial counting.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 18/01/2018
 * @version 1.0
 */

public class Factorial {
    /**
     * Factorial counting
     * @param n - the number which factorial needed.
     * @return factorial or '-1' if n < 0.
     */
    public int calc(int n) {
        int fact = 1;
        if (n < 0) {
            fact = -1;
        }
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}