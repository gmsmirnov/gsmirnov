package ru.job4j.sqlite;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class Config - configuration to work with SQL Lite.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/11/2018
 */
public class Config {
    /**
     * THe properties.
     */
    private final Properties values = new Properties();

    /**
     * Creates the config for connection to database with property file.
     */
    public Config() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("sqlite.properties")) {
            this.values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Gets the path to database.
     *
     * @param key - the key of property to get the path to database.
     * @return the path to database.
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }
}