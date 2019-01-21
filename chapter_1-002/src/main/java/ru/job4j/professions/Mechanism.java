package ru.job4j.professions;

/**
 * Class that describes mechanism.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/02/2018
 */
public class Mechanism {
    /**
     * The name of mechanism.
     */
    String name;

    /**
     * The condition of mechanism.
     */
    int condition;

    /**
     * Constructor.
     * @param name - the name of mechanism.
     */
    public Mechanism(String name) {
        this.name = name;
        this.condition = 100;
    }

    /**
     * Mechanism working process.
     * @return process description.
     */
    public String work() {
        return "Mechanism " + this.getName() + " worked well, its condition is " + --this.condition;
    }

    /**
     * Name setter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Condition setter.
     */
    public void setCondition(int condition) {
        this.condition = condition;
    }

    /**
     * Name getter.
     * @return mechanisms name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Condition getter.
     * @return condition level.
     */
    public int getCondition() {
        return this.condition;
    }
}