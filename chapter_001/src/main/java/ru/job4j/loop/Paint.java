package ru.job4j.loop;

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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((j < height - i - 1) || (j >= height + i)) {
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