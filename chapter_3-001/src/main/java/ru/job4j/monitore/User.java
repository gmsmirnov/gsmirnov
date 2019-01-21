package ru.job4j.monitore;

/**
 * User's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 02/08/2018
 */
public class User {
    /**
     * User's id.
     */
    private final int id;

    /**
     * User's amount.
     */
    private int amount;

    /**
     * Creates new user.
     *
     * @param id - the user's unique id.
     */
    public User(int id) {
        this.id = id;
        this.amount = 0;
    }

    /**
     * Creates new user.
     *
     * @param id - the user's unique id.
     * @param amount - the user's amount.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Gets user's id.
     *
     * @return user's id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets user's amount.
     *
     * @return user's amount.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Compare this user with another.
     *
     * @param o - another user.
     * @return true if users are equals.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            User user = (User) o;
            result = (this.id == user.id);
        }
        return result;
    }

    /**
     * Calculates user's hashCode.
     *
     * @return the user's hashCode.
     */
    @Override
    public int hashCode() {
        return this.id;
    }
}