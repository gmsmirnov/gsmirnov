package ru.job4j.loop;

/**
 * Chess board.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 19/01/2018
 * @version 1.0
 */
public class Board {
    /**
     * Chaee board painting
     * @param width - the width of chess board.
     * @param height - the height of chess board.
     * @return String, that presents pseudo graphic chess board.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}