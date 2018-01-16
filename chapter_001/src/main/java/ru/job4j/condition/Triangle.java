package ru.job4j.condition;

/**
 * Class that describes triangle.
 * @author Gregory Smirnov (artress@ngs.ru)
 * @since 16/01/2018
 * @version 1.0
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    /**
     * Constructor, creates a triangle by three points.
     * @param a - the first conner point.
     * @param b - the second conner point.
     * @param c - the third conner point.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Half-perimeter calculation.
     * @param ab - distance from a-point to b-point.
     * @param bc - distance from b-point to c-point.
     * @param ca - distance from c-point to a-point.
     * @return - Half-perimeter.
     */
    public double period(double ab, double bc, double ca) {
        return (ab + bc + ca) / 2;
    }

    /**
     * Triangle area calculation.
     * @return - triangle area if triangle exists or '-1' if not.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distance(this.b);
        double bc = this.b.distance(this.c);
        double ca = this.c.distance(this.a);
        double halfPerimeter = this.period(ab, bc, ca);
        if (this.exist(ab, bc, ca)) {
            rsl = Math.sqrt(halfPerimeter * (halfPerimeter - ab) * (halfPerimeter - bc) * (halfPerimeter - ca));
        }
        return rsl;
    }

    /**
     * Verification of existence.
     * @param ab - distance from a-point to b-point.
     * @param bc - distance from b-point to c-point.
     * @param ca - distance from c-point to a-point.
     * @return - 'true' if triangle exists or 'false' if not
     */
    private boolean exist(double ab, double bc, double ca) {
        boolean ifExist = false;
        if ((ab + bc > ca) && (bc + ca > ab) && (ca + ab > bc)) {
            ifExist = true;
        }
        return ifExist;
    }
}