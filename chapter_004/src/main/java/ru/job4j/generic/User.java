package ru.job4j.generic;

/**
 * The user element description extended from Base.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/05/2018
 */
public class User extends Base {
    /**
     * User's name.
     */
    private final String name;

    /**
     * User's age.
     */
    private int age;

    /**
     * The user constructor.
     *
     * @param id - the id of the user.
     * @param name - name of the user.
     * @param age - the age of the user.
     */
    public User(String id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    /**
     * Gets the id of the user.
     *
     * @return the id of the user.
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     * Compare this user with other user.
     *
     * @param o - other user.
     * @return true if equals.
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
            if (age != user.age) {
                result = false;
            } else {
                result = name != null ? name.equals(user.name) : user.name == null;
            }
        }
        return result;
    }

    /**
     * Calculates the hash code of this user.
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
     * Presents the user in string-view.
     *
     * @return the string-view of user.
     */
    @Override
    public String toString() {
        return String.format("User{name='%s', age=%d}", this.name, this.age);
    }
}