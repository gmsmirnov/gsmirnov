package ru.job4j.condition;

/**
 * Class that describes point.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 15/01/2018
 * @version 1.0
 */
public class Point {
    private int x;
    private int y;

    /**
     * Constructor, creates a point with coordinates (x; y).
     * @param x - the x coordinate.
     * @param y - the y coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor, creates a point with coordinates (0; 0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Method for calculating distance between two points: the origin point and the given point.
     * @param that - the given point.
     */
    public double distance(Point that) {
        return Math.sqrt(Math.pow(that.x - this.x, 2.0) + Math.pow(that.y - this.y, 2.0));
    }
}