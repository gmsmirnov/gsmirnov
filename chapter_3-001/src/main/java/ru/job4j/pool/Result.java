package ru.job4j.pool;

import java.util.LinkedList;
import java.util.concurrent.Future;

/**
 * A e-mail notification result.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 19/09/2018
 */
public class Result {
    /**
     * The task's results.
     */
    private LinkedList<Future<Boolean>> results;

    /**
     * The constructor. Creates new list of Future<Boolean>
     */
    public Result() {
        this.results = new LinkedList<Future<Boolean>>();
    }

    /**
     * Gets task's results.
     *
     * @return the list of results.
     */
    public LinkedList<Future<Boolean>> getResults() {
        return this.results;
    }

    /**
     * Adds new result to the list.
     *
     * @param newResult - the new result.
     */
    public void add(Future<Boolean> newResult) {
        this.results.add(newResult);
    }
}