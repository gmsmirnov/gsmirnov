package ru.job4j.professions;

/**
 * Class that describes teacher, extends profession class.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/02/2018
 */
public class Teacher extends Profession {
    /**
     * The patience of teacher. When patience is high, teacher punishes pupil.
     */
    private int patience;

    /**
     * The power of teacher teach ability.
     */
    private int teachLvl;

    /**
     * Constructor.
     * @param name - the name of teacher.
     * @param teachLvl - the power of teacher teach ability.
     */
    public Teacher(String name, int teachLvl) {
        this.name = name;
        this.teachLvl = teachLvl;
        this.patience = 0;
    }

    /**
     * Teaching process. Increases humans education level.
     * @return process description.
     */
    private String teach(Human human) {
        this.patience++;
        human.educationLvl += this.teachLvl;
        return "Teacher " + this.getName() + " teaches " + human.getName() + " to " + human.educationLvl + " level";
    }

    /**
     * Punishing process. Decrease humans health level.
     * @return process description.
     */
    private String punish(Human human) {
        this.patience = 0;
        return "Teacher " + this.getName() + " punishes " + human.getName() + " to " + --human.healthLvl + " heals level";
    }

    /**
     * Teacher working process.
     * @return process description.
     */
    public String teacherWorks(Human human) {
        String result;
        if (this.patience >= 10) {
            result = this.punish(human);
        } else {
            result = this.teach(human);
        }
        return this.work() + result;
    }
}