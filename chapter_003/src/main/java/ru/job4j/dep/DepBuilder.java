package ru.job4j.dep;

/**
 * Department builder. Builds new department from two parameters.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 2.0
 * @since 23/04/2018
 */
public class DepBuilder {
    /**
     * Builds new department by addition to exciting department another department and separation character.
     *
     * @param dep1 - firs department (for example K1\SK1).
     * @param dep2 - second department (for example SSK1).
     * @return new department (for example K1\SK1\SSK1).
     */
    public static Dep add(Dep dep1, Dep dep2) {
        return new Dep(String.format("%s\\%s", dep1.getDep(), dep2.getDep()));
    }
}