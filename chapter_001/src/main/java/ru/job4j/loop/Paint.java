package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Pyramid painting.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 19/01/2018
 * @version 1.1
 */
public class Paint {
    /**
     * Pyramid painting
     * @param height - the height of pyramid.
     * @return String, that presents pseudo graphic pyramid.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        int width = height * 2 - 1;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if ((column < height - row - 1) || (column >= height + row)) {
                    screen.append(" ");
                } else {
                    screen.append("^");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }

    /**
     * Left triangle of pyramid painting
     * @param height - the height of pyramid (triangle).
     * @return String, that presents pseudo graphic triangle.
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        int width  = height;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (column < width - row - 1) {
                    screen.append(" ");
                } else {
                    screen.append("^");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }

    /**
     * Right triangle of pyramid painting
     * @param height - the height of pyramid (triangle).
     * @return String, that presents pseudo graphic triangle.
     */
    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        int width  = height;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (column > row) {
                    screen.append(" ");
                } else {
                    screen.append("^");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}