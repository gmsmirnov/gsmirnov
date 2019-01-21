package ru.job4j.pseudo;

/**
 * Class Square for picturing pseudo square figure.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 15/02/2018
 */
public class Square implements Shape {
    /**
     * The size of square.
     */
    private int size;

    /**
     * Square constructor.
     *
     * @param size - the size of square.
     * @throws IllegalArgumentException if size <= 0.
     */
    public Square(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be > 0.");
        }
        this.size = size;
    }

    /**
     * Size setter.
     *
     * @param size - the size of aquare.
     * @throws IllegalArgumentException if size <= 0.
     */
    public void setSize(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be > 0.");
        }
        this.size = size;
    }

    /**
     * Size getter.
     *
     * @return the size of square.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Creates pseudo graphic picture of square.
     *
     * @return pseudo graphic picture of square.
     */
    @Override
    public String pic() {
        StringBuilder picture = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                picture.append("+");
            }
            picture.append(System.lineSeparator());
        }
        return picture.toString();
    }
}