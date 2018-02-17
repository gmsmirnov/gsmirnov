package ru.job4j.pseudo;

/**
 * Class Triangle for picturing pseudo triangle figure.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/02/2018
 */
public class Triangle implements Shape {
    /**
     * The height of triangle.
     */
    private int height;

    /**
     * Constructor for triangle pseudo figure.
     *
     * @param height - the height of triangle.
     * @throws IllegalArgumentException if height <= 0.
     */
    public Triangle(int height) throws IllegalArgumentException {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be > 0.");
        }
        this.height = height;
    }

    /**
     * Height setter.
     *
     * @param height - the height of triangle.
     * @throws IllegalArgumentException if height <= 0.
     */
    public void setHeight(int height) throws IllegalArgumentException {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be > 0.");
        }
        this.height = height;
    }

    /**
     * Height getter.
     *
     * @return the height of triangle.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Creates pseudo graphic picture of triangle.
     *
     * @return pseudo graphic picture of triangle.
     */
    @Override
    public String pic() {
        StringBuilder picture = new StringBuilder();
        int base = this.height * 2 - 1;
        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < base; column++) {
                if ((column < base / 2 - row) || (column > base / 2 + row)) {
                    picture.append(" ");
                } else {
                    picture.append("+");
                }
            }
            picture.append(System.lineSeparator());
        }
        return picture.toString();
    }
}