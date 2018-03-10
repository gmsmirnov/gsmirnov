package ru.job4j.convert;

import java.util.UUID;

/**
 * Describes user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/03/2018
 */
public class User {
    /**
     * The users ID.
     */
    private UUID id;

    /**
     * The users name.
     */
    private String name;

    /**
     * The city where user lives.
     */
    private String city;

    /**
     * User constructor. Generate ID automatic.
     *
     * @param name - user name.
     * @param city - city where user lives.
     */
    public User(String name, String city) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.city = city;
    }

    /**
     * Gets user ID.
     *
     * @return - the users ID.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets user name.
     *
     * @return - the users name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the city where user lives.
     *
     * @return - the city where user lives.
     */
    public String getCity() {
        return this.city;
    }
}
