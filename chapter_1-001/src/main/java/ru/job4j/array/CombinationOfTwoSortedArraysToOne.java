package ru.job4j.array;

import java.util.Arrays;

/**
 * This class combines two sorted arrays in one sorted array.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 02/02/2018
 */
public class CombinationOfTwoSortedArraysToOne {
    /**
     * Combines two sorted arrays in one.
     * @param firstArray - the first sorted array.
     * @param secondArray - the second sorted array.
     * @throws IllegalArgumentException when one of params is 0-length.
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

    /**
     * Combines two sorted arrays in one.
     * @param firstArray - the first sorted array.
     * @param secondArray - the second sorted array.
     * @throws IllegalArgumentException when one of params is 0-length.
     * @return new sorted array.
     */
    public int[] arraysCombination2(int[] firstArray, int[] secondArray) {
        if ((firstArray == null) || (secondArray == null)) {
            throw new IllegalArgumentException("One of arrays is 0-length.");
        }
        int[] newArray = new int[firstArray.length + secondArray.length];
        for (int k = 0, i = 0, j = 0; k < newArray.length; k++) {
            if ((i < firstArray.length) && (j < secondArray.length) && (firstArray[i] <= secondArray[j])) {
                newArray[k] = firstArray[i];
                i++;
                continue;
            } else if ((i < firstArray.length) && (j < secondArray.length) && (firstArray[i] > secondArray[j])) {
                newArray[k] = secondArray[j];
                j++;
                continue;
            }
            if (i == firstArray.length) {
                newArray[k] = secondArray[j];
                j++;
            } else if (j == secondArray.length) {
                newArray[k] = firstArray[i];
                i++;
            }
        }
        return newArray;
    }

    /**
     * Combines two sorted arrays in one.
     * @param firstArray - the first sorted array.
     * @param secondArray - the second sorted array.
     * @throws IllegalArgumentException when one of params is 0-length.
     * @return new sorted array.
     */
    public int[] arraysCombination3(int[] firstArray, int[] secondArray) {
        if ((firstArray == null) || (secondArray == null)) {
            throw new IllegalArgumentException("One of arrays is 0-length.");
        }
        int[] newArray = new int[firstArray.length + secondArray.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while ((i < firstArray.length) && (j < secondArray.length)) {
            if (firstArray[i] <= secondArray[j]) {
                newArray[k++] = firstArray[i++];
            } else if (firstArray[i] > secondArray[j]) {
                newArray[k++] = secondArray[j++];
            }
        }
        if (i == firstArray.length) {
            System.arraycopy(secondArray, j, newArray, k, secondArray.length - j);
        } else if (j == secondArray.length) {
            System.arraycopy(firstArray, i, newArray, k, firstArray.length - i);
        }
        return newArray;
    }
}