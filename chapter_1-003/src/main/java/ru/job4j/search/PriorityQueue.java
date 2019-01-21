package ru.job4j.search;

import java.util.LinkedList;

/**
 * Describes the list of daily tasks.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 09/03/2018
 */
public class PriorityQueue {
    /**
     * The list of daily tasks.
     */
    private final LinkedList<Task> tasks = new LinkedList<Task>();

    /**
     * Puts the daily task into position which determine by tasks priority field.
     *
     * @param task - the daily task.
     */
    public void put(Task task) {
        int index = 0;
        for (Task current : this.tasks) {
            if (this.tasks.isEmpty() || task.getPriority() < current.getPriority()) {
                break;
            }
            index++;
        }
        this.tasks.add(index, task);
    }

    /**
     * Takes the most important task in the list. If the list is empty, returns null!!!
     *
     * @return - the most important task.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
