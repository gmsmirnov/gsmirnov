package ru.job4j.parser;

/**
 * The description of a vacancy.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/12/2018
 */
public class Vacancy {
    /**
     * A vacancy's name.
     */
    private String name;

    /**
     * A vacancy's description.
     */
    private String description;

    /**
     * A link (url) to a vacancy.
     */
    private String link;

    /**
     * The vacancy's constructor.
     *
     * @param name - the vacancy's name.
     * @param description - the vacancy's description.
     */
    public Vacancy(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    /**
     * Gets the vacancy's name.
     *
     * @return - the vacancy's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the vacancy's name.
     *
     * @param name - the specified vacancy's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the vacancy's description.
     *
     * @return the vacancy's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the vacancy's description.
     *
     * @param description - the specified vacancy's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the vacancy's link (url to this vacancy).
     *
     * @return the vacancy's link (url to this vacancy).
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Sets the vacancy's link (url to this vacancy).
     *
     * @param link - the specified vacancy's link (url to this vacancy).
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Checks this vacancy for equivalence to another vacancy.
     * Vacancies are equals if names and descriptions are equals.
     * The links doesn't matter.
     *
     * @param o - another vacancy.
     * @return - true these vacancies are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Vacancy vacancy = (Vacancy) o;
            if (this.name != null ? !this.name.equals(vacancy.name) : vacancy.name != null) {
                result = false;
            } else {
                result = this.description != null ? this.description.equals(vacancy.description) : vacancy.description == null;
            }
        }
        return result;
    }

    /**
     * Calculates the vacancy's hashcode.
     *
     * @return - the vacancy's hashcode.
     */
    @Override
    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        return result;
    }

    /**
     * Creates a string-view of a vacancy.
     *
     * @return a string-view of a vacancy.
     */
    @Override
    public String toString() {
        return String.format("Vacancy%nname='%s'%ndescription='%s'%nlink='%s'%n", this.name, this.description, this.link);
    }
}