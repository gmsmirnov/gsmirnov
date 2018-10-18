package ru.job4j.nonblock;

/**
 * The Base model.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/08/2018
 */
public class Base {
    /**
     * The id.
     */
    private final int id;

    /**
     * The version.
     */
    private final int version;

    /**
     * Creates new model with the specified id and zero version.
     *
     * @param id - the specified id.
     */
    public Base(int id) {
        this.id = id;
        this.version = 0;
    }

    /**
     * Creates new model with the specified id and zero version.
     *
     * @param id - the specified id.
     * @param version - the specified version.
     */
    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    /**
     * Gets current model's id.
     *
     * @return the model's id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets current model's version.
     *
     * @return the model's version.
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Presents model in string view.
     *
     * @return the string view.
     */
    @Override
    public String toString() {
        return String.format("Base{id=%d, version=%d}", this.id, this.version);
    }

    /**
     * Compares this model with other model.
     *
     * @param o - the other model
     * @return true if models are equals.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Base base = (Base) o;
            if (this.id != base.id) {
                result = false;
            } else {
                result = this.version == base.version;
            }
        }
        return result;
    }

    /**
     * Calculates model's hashcode.
     *
     * @return model's hashcode.
     */
    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + this.version;
        return result;
    }
}