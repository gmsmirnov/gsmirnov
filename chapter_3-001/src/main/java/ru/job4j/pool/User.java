package ru.job4j.pool;

/**
 * A user's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 01/09/2018
 */
public class User {
    /**
     * The user's name.
     */
    private final String userName;

    /**
     * The user's e-mail.
     */
    private final String eMail;

    /**
     * The constructor. Creates a user with the specified name and e-mail.
     *
     * @param userName - the specified user's name.
     * @param eMail - the specified user's e-mail.
     */
    public User(String userName, String eMail) {
        this.userName = userName;
        this.eMail = eMail;
    }

    /**
     * Gets a user's name.
     *
     * @return the user's name.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Gets a user's e-mail.
     *
     * @return the user's e-mail.
     */
    public String geteMail() {
        return this.eMail;
    }
}