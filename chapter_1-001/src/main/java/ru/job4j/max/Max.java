package ru.job4j.max;

/**
 * Class that helps to find maximum value.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 16/01/2018
 * @version 1.1
 */
public class Max {

    /**
     * Max of two values.
     * @param firstValue - the first value.
     * @param secondValue - the second value.
     */
    public int max(int firstValue, int secondValue) {
        return (firstValue >= secondValue) ? firstValue : secondValue;
    }

    /**
     * Max of three values.
     * @param firstValue - the first value.
     * @param secondValue - the second value.
     * @param thirdValue - the second value.
     */
    public int max(int firstValue, int secondValue, int thirdValue) {
        return this.max(this.max(firstValue, secondValue), thirdValue);
    }
}