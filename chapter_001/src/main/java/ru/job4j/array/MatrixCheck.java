package ru.job4j.array;

/**
 * Checks if boolean diagonals (one or both) in the array are the same.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 04/07/2018
 * @version 1.0
 */
public class MatrixCheck {
    /**
     * Checks if one of diagonals in the array contains only the same booleans. E.g. if all diagonal elements are 'true',
     * or all diagonal elements are 'false'.
     *
     * @param data - the quadratic array (matrix).
     * @throws IllegalArgumentException when parameter isn't a square-array.
     * @return true if one of diagonals contains only the same booleans.
     */
    public boolean mono(boolean[][] data) {
        boolean result;
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        if (!this.isMatrix(data)) {
            throw new IllegalArgumentException("The array isn't a square-array!");
        } else {
            for (int index = 0; index < data[0].length - 1; index++) {
                if (data[index][index] != data[index + 1][index + 1]) {
                    diagonal1 = false;
                }
                if (data[data.length - index - 1][index] != data[data.length - index - 2][index + 1]) {
                    diagonal2 = false;
                }
            }
            result = diagonal1 || diagonal2;
        }
        return result;
    }

    /**
     * Checks if the array is matrix (quadratic array) or not.
     *
     * @param data - the specifies array.
     * @return true if the specified array is matrix.
     */
    private boolean isMatrix(boolean[][] data) {
        boolean result = true;
        if (data.length != data[0].length) {
            result = false;
        }
        return result;
    }
}