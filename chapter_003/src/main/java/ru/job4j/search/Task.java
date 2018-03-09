package ru.job4j.search;

/**
 * Describes daily tasks.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/03/2018
 */
public class Task {
    /**
     * Task description.
     */
    private String desc;

    /**
     * Task priority.
     */
    private int priority;

    /**
     * Task constructor.
     *
     * @param desc - task description.
     * @param priority - task priority.
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    /**
     * Gets task description.
     *
     * @return - task description.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Gets task priority.
     *
     * @return - task priority.
     */
    public int getPriority() {
        return this.priority;
    }
}
