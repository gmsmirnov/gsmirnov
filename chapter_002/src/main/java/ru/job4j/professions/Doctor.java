package ru.job4j.professions;

/**
 * Class that describes doctor, extends profession class.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/02/2018
 */
public class Doctor extends Profession {
    /**
     * The power of doctor heal ability.
     */
    private int healLvl;

    /**
     * Constructor.
     * @param name - the name of doctor.
     * @param educationLvl - the doctor education level.
     * @param healLvl - the power of doctor heal ability.
     */
    public Doctor(String name, int educationLvl, int healLvl) {
        super(name, educationLvl);
        this.healLvl = healLvl;
    }

    /**
     * Healing process. Restores human health level.
     * @return process description.
     */
    private String heal(Human human) {
        human.healthLvl += this.healLvl;
        return "Doctor " + this.getName() + " heals " + human.getName() + " to " + human.getHealthLvl() + " health lvl";
    }

    /**
     * Doctor working process.
     * @return process description.
     */
    public String doctorWorks(Human human) {
        return this.work() + this.heal(human);
    }
}