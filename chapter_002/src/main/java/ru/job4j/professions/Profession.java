package ru.job4j.professions;

/**
 * Basic class that describes professions extends human.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/02/2018
 */
public class Profession extends Human {
    /**
     * Default constructor.
     */
    public Profession() {
        this.name = "Idiot";
        this.healthLvl = 100;
        this.educationLvl = 10;
        this.fatigue = 0;
    }

    /**
     * Constructor.
     * @param name - the name of human (professional).
     * @param educationLvl - the human (professional) education level.
     */
    public Profession(String name, int educationLvl) {
        this.name = name;
        this.educationLvl = educationLvl;
    }

    /**
     * Working process.
     * @return process description.
     */
    protected String work() {
        if (this.fatigue > 10) {
            this.eat();
            this.sleep();
        }
        this.fatigue++;
        return this.getName() + " works... ";
    }
}