package ru.job4j.tracker;

/**
 * The description of user's model.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/12/2018
 */
public class User {
    /**
     * User's name.
     */
    private final String name;

    /**
     * User's password.
     */
    private String password;

    /**
     * User's role.
     */
    private String role;

    /**
     * Creates new user with the specified parameters.
     *
     * @param name - the specified name.
     * @param password - the specified password.
     * @param role - the specified role.
     */
    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the user's name.
     *
     * @return the user's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Updates the user's password.
     *
     * @param password - the new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's role.
     *
     * @return the user's role.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Updates the user's role.
     *
     * @param role - the new role.
     */
    public void setRole(String role) {
        this.role = role;
    }
}