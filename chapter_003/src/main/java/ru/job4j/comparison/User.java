package ru.job4j.comparison;

/**
 * Describes user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/03/2018
 */
public class User implements Comparable<User> {
    /**
     * The users name.
     */
    private String name;

    /**
     * The users age.
     */
    private int age;

    /**
     * Users constructor.
     *
     * @param name - the name of user.
     * @param age - the age of user.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Name getter.
     *
     * @return - the name of user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Age getter.
     *
     * @return - the age of user.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Converts user to String format.
     *
     * @return - the String with users params.
     */
    @Override
    public String toString() {
        return String.format("User: %s | ages: %s%n", this.getName(), this.getAge());
    }

    /**
     * Equivalence of users.
     *
     * @param other - the user to compare.
     * @return true if users are equals.
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
            if (age != user.age) {
                result = false;
            } else {
                result = name != null ? name.equals(user.name) : user.name == null;
            }
        }
        return result;
    }

    /**
     * Calculates hash code.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    /**
     * Comparison of users.
     *
     * @param user - the user to compare with THIS user.
     * @return - "1" if THIS user is younger or higher in alphabetical order;
     * "-1" if user-param is younger or higher in alphabetical order;
     * "0" if ages and names are equals.
     */
    @Override
    public int compareTo(User user) {
        int result;
        if (user != null && this.age > user.age) {
            result = 1;
        } else if (this.age < user.age) {
            result = -1;
        } else {
            result = this.name.compareTo(user.name);
        }
        return result;
    }
}