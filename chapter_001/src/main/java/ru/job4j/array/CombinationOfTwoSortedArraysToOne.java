package ru.job4j.array;

/**
 * This class combines two sorted arrays in one sorted array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/02/2018
 */
public class CombinationOfTwoSortedArraysToOne {
    /**
     * Combines two sorted arrays in one.
     * @param firstArray - the first sorted array.
     * @param secondArray - the second sorted array.
     * @return new sorted array.
     */
    public int[] arraysCombination(int[] firstArray, int[] secondArray) {
        if ((firstArray == null) || (secondArray == null)) {
            throw new IllegalArgumentException("One of arrays is 0-length.");
        }
        int[] newArray;
        if (firstArray[firstArray.length - 1] > secondArray[secondArray.length - 1]) {
            newArray = firstArray;
            firstArray = secondArray;
            secondArray = newArray;
        }
        newArray = new int[firstArray.length + secondArray.length];
        int counterFirstArray = 0;
        int newArrayIndex = 0;
        for (int i = 0; i < secondArray.length; i++) {
            for (int j = counterFirstArray; j < firstArray.length; j++) {
                if (secondArray[i] >= firstArray[j]) {
                    newArray[newArrayIndex++] = firstArray[j];
                    counterFirstArray++;
                } else {
                    newArray[newArrayIndex++] = secondArray[i];
                    break;
                }
            }
            if (counterFirstArray == firstArray.length) {
                newArray[newArrayIndex++] = secondArray[i];
            }
        }
        return newArray;
    }
}
