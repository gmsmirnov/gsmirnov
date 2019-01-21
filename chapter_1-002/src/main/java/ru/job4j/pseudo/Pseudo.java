package ru.job4j.pseudo;

/**
 * Demonstrates pseudo graphic figures on screen.
 */
public class Pseudo {
    /**
     * Entry point.
     * @param args - input parameters.
     */
    public static void main(String[] args) {
        Paint paint = new Paint();
        Shape[] shapes = new Shape[] {new Triangle(7), new Square(10)};
        for (Shape shape : shapes) {
            paint.draw(shape);
            System.out.println();
        }
    }
}