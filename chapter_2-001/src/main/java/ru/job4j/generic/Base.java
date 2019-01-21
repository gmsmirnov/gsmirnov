package ru.job4j.generic;

/**
 * Base type.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/05/2018
 */
public abstract class Base {
    /**
     * The id of base type element.
     */
    private final String id;

    /**
     * Base element constructor.
     *
     * @param id
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id.
     */
    public String getId() {
        return this.id;
    }
}