package ru.job4j.bank;

/**
 * Describes bank user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 04/04/2018
 */
public class User {
    /**
     * User name.
     */
    private final String name;

    /**
     * User passport.
     */
    private final String passport;

    /**
     * User construction.
     *
     * @param name - the users name.
     * @param passport - the users passport.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Gets user passport.
     *
     * @return - users passport.
     */
    public String getPassport() {
        return this.passport;
    }

    /**
     * Users comparison.
     *
     * @param other - other user.
     * @return - true if users are equals.
     */
    @Override
    public boolean equals(Object other) {
        boolean result;
        if (this == other) {
            result = true;
        } else if (other == null || getClass() != other.getClass()) {
            result = false;
        } else {
            User user = (User) other;
            result = this.passport.equals(user.passport);
        }
        return result;
    }

    /**
     * Calculate user hash code.
     *
     * @return users hash code.
     */
    @Override
    public int hashCode() {
        return 31 + passport.hashCode();
    }

    /**
     * Presents user in string view.
     *
     * @return - user in string view.
     */
    @Override
    public String toString() {
        return String.format("User{name='%s', passport='%s'}", this.name, this.passport);
    }
}