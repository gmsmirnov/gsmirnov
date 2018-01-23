package ru.job4j.array;

/**
 * Matrix.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 23/01/2018
 * @version 1.0
 */
public class Matrix {
    /**
     * Creating the multiplication table from matrix.
     * @param size - the size of the matrix.
     * @return the multiplication table.
     */
    public int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i) * (j);
            }
        }
        return matrix;
    }
}