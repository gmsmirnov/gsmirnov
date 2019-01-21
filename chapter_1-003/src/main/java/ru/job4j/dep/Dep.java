package ru.job4j.dep;

/**
 * Department description. Department is immutable object. All modification creates by building
 * new departments with DepBuilder class. Inheritance is rejected.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 2.0
 * @since 23/04/2018
 */
public final class Dep {
    /**
     * The departments name.
     */
    private final String dep;

    /**
     * Department constructor.
     *
     * @param dep - string with departments name.
     */
    public Dep(String dep) {
        this.dep = dep;
    }

    /**
     * Gers this department.
     *
     * @return this department.
     */
    public String getDep() {
        return this.dep;
    }

    /**
     * Presents department in string view.
     *
     * @return this department in string view.
     */
    @Override
    public String toString() {
        return String.format("%s", this.dep);
    }

    /**
     * Compare this department with another.
     *
     * @param o - other department.
     * @return true if departments are equals.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Dep dep1 = (Dep) o;
            result = dep.equals(dep1.dep);
        }
        return result;
    }

    /**
     * Gets departments hash code.
     *
     * @return gash code.
     */
    @Override
    public int hashCode() {
        return dep.hashCode();
    }
}