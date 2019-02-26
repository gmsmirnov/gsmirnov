package ru.job4j.servlets.model;

import java.util.Date;
import java.util.Objects;

/**
 * User model description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 07/02/2019
 */
public class User {
    /**
     * Param "id" in in the POST request.
     */
    public static final String PARAM_ID = "id";

    /**
     * Param "name" in in the POST request.
     */
    public static final String PARAM_NAME = "name";

    /**
     * Param "login" in in the POST request.
     */
    public static final String PARAM_LOGIN = "login";

    /**
     * Param "email" in in the POST request.
     */
    public static final String PARAM_EMAIL = "email";

    /**
     * User's id.
     */
    private int id;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's login.
     */
    private String login;

    /**
     * User's e-mail.
     */
    private String email;

    /**
     * User's creation date.
     */
    private Date createDate;

    /**
     * Creates a new user with the specified params.
     *
     * @param id - the specified user's id.
     * @param name - the specified user's name.
     * @param login - the specified user's login.
     * @param email - the specified user's email.
     */
    public User(String id, String name, String login, String email) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date();
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
     * Sets the specified user's id.
     *
     * @param id - the specified new user's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user's name.
     *
     * @return user's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the specified user's name.
     *
     * @param name - the specified user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets user's login.
     *
     * @return user's login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Sets the specified user's login.
     *
     * @param login - the specified user's login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets user's e-mail.
     *
     * @return user's e-mail.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the specified user's e-mail.
     *
     * @param email - the specified user's e-mail.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets user's creation date.
     *
     * @return user's creation date.
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * Sets the specified user's creation date
     *
     * @param createDate - the specified user's creation date.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Checks this user equivalence to the specified user.
     *
     * @param o - the specified user.
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
            result = this.id == user.id
                    && Objects.equals(this.name, user.name)
                    && Objects.equals(this.login, user.login)
                    && Objects.equals(this.email, user.email);
        }
        return result;
    }

    /**
     * Calculates this user's hash code.
     *
     * @return this user's hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.login, this.email);
    }

    /**
     * Presents this user in a String-view.
     *
     * @return this user's String-view.
     */
    @Override
    public String toString() {
        return String.format("%nUser {id=%d, name='%s, login='%s, email='%s, createDate=%s}%n",
                this.id, this.name, this.login, this.email, this.createDate);
    }
}