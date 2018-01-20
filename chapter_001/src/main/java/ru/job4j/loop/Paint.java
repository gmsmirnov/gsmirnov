package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Pyramid painting.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 19/01/2018
 * @version 1.2
 */
public class Paint {
    /**
     * Pyramid painting
     * @param height - the height of pyramid.
     * @return String, that presents pseudo graphic pyramid.
     */
    public String pyramid(int height) {
        int width = height * 2 - 1;
        return this.loopBy(
                height,
                width,
                (row, column) -> (column < height - row - 1) || (column >= height + row)
        );
    }

    /**
     * Left triangle of pyramid painting
     * @param height - the height of pyramid (triangle).
     * @return String, that presents pseudo graphic triangle.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> column < height - row - 1
        );
    }

    /**
     * Right triangle of pyramid painting
     * @param height - the height of pyramid (triangle).
     * @return String, that presents pseudo graphic triangle.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> column > row
        );
    }

    /**
     * Pyramid or triangle painting
     * @param height - the height of pyramid (triangle).
     * @param width - the width of pyramid (triangle).
     * @param predict - condition of '^'-paint.
     * @return String, that presents pseudo graphic pyramid.
     */
    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (predict.test(row, column)) {
                    screen.append(" ");
                } else {
                    screen.append("^");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}