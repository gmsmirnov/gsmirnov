package ru.job4j.professions;

/**
 * Class that describes human.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/02/2018
 */
public class Human {
    /**
     * The name of human.
     */
    protected String name;

    /**
     * The education level of human.
     */
    protected int educationLvl;

    /**
     * The fatigue index. When human is tired, he needs to eat and sleep.
     */
    protected int fatigue;

    /**
     * The health level.
     */
    protected int healthLvl;

    /**
     * Default constructor.
     */
    public Human() {
        this.name = "Idiot";
        this.healthLvl = 100;
        this.educationLvl = 10;
        this.fatigue = 0;
    }

    /**
     * Constructor.
     * @param name - the name of human.
     * @param educationLvl - the human education level.
     */
    public Human(String name, int educationLvl) {
        this.name = name;
        this.healthLvl = 100;
        this.educationLvl = educationLvl;
        this.fatigue = 0;
    }

    /**
     * Name setter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Health level setter.
     */
    public void setHealthLvl(int healthLvl) {
        this.healthLvl = healthLvl;
    }

    /**
     * Education level setter.
     */
    public void setEducationLvl(int educationLvl) {
        this.educationLvl = educationLvl;
    }

    /**
     * Fatigue index setter.
     */
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    /**
     * Name getter.
     * @return humans name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Health level getter.
     * @return health level.
     */
    public int getHealthLvl() {
        return this.healthLvl;
    }

    /**
     * Education level getter.
     * @return education level.
     */
    public int getEducationLvl() {
        return this.educationLvl;
    }

    /**
     * Fatigue index getter.
     * @return fatigue index.
     */
    public int getFatigue() {
        return this.fatigue;
    }

    /**
     * Eating process. Fatigue index restoration.
     * @return process description.
     */
    protected String eat() {
        this.fatigue -= 1;
        return this.name + " eats well, its fatigue is " + this.getFatigue();
    }

    /**
     * Sleeping process. Fatigue index restoration.
     * @return process description.
     */
    protected String sleep() {
        this.fatigue -= 2;
        return this.name + " sleeps well, its fatigue is " + this.getFatigue();
    }
}