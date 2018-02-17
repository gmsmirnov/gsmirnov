package ru.job4j.pseudo;

/**
 * Class Paint draws shape.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/02/2018
 */
public class Paint {
    /**
     * Draws pseudo graphic shape.
     *
     * @param shape the shape to draw.
     */
    public void draw(Shape shape) {
        System.out.print(shape.pic());
    }
}