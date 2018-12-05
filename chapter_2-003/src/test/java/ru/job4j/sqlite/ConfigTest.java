package ru.job4j.sqlite;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class Config - testing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/11/2018
 */
public class ConfigTest {
    @Test
    public void whenCreateConfigThenGetURLGetsRightURL() {
        Config config = new Config();
        assertThat(config.get("url"), is("jdbc:sqlite:C:/sqlite/db/magnit.db"));
    }
}