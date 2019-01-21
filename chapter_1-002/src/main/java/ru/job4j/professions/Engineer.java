package ru.job4j.professions;

/**
 * Class that describes engineer, extends profession class.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/02/2018
 */
public class Engineer extends Profession {
    /**
     * The power of engineers repair ability.
     */
    private int repairLvl;

    /**
     * Constructor.
     * @param name - the name of engineer.
     * @param educationLvl - the engineer education level.
     * @param repairLvl - the power of engineers repair ability.
     */
    public Engineer(String name, int educationLvl, int repairLvl) {
        super(name, educationLvl);
        this.repairLvl = repairLvl;
    }

    /**
     * Repairing process. Restores mechanism condition level.
     * @return process description.
     */
    private String repair(Mechanism mechanism) {
        mechanism.condition += repairLvl;
        return "Engineer " + this.getName() + " repairs mechanism " + mechanism.getName() + " to " + mechanism.getCondition() + " %";
    }

    /**
     * Mechanism creation process.
     * @return new mechanism.
     */
    public Mechanism createNewMechanism(String name) {
        return new Mechanism(name);
    }

    /**
     * Engineer working process.
     * @return process description.
     */
    public String engineerWorks(Mechanism mechanism) {
        return this.work() + this.repair(mechanism);
    }
}