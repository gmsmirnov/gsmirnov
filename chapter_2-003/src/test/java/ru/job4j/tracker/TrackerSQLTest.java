package ru.job4j.tracker;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import com.ibatis.common.jdbc.ScriptRunner;

/**
 * The test class for TrackerSQL.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 18/10/2018
 */
public class TrackerSQLTest {
    private TrackerSQL sql;
    private Logger testLog;

    @Before
    public void init() {
        this.testLog = Logger.getLogger(TrackerSQLTest.class);
        this.sql = new TrackerSQL();
        this.testLog.info("TrackerSQLTest started.");
    }

    @After
    public void end() throws IOException {
        this.sql.close();
        this.testLog.info("TrackerSQLTest ended.");
    }

    @Test
    public void whenConnectedThenTrue() {
        this.testLog.info("Test 'whenConnectedThenTrue()' started.");
        assertThat(this.sql.isConnected(), is(true));
        this.testLog.info("Test 'whenConnectedThenTrue()' ended.");
    }

    @Test
    public void whenAddsItemsThenDataBaseHasThoseItems() {
        this.testLog.info("Test: whenAddsItemsThenDataBaseHasThoseItems() started.");
        this.initializeDBWithScript();
        assertThat(this.sql.findAll().size(), is(5));
        assertThat(this.sql.findByName("test").size(), is(3));
        this.testLog.info("Test: whenAddsItemsThenDataBaseHasThoseItems() ended.");
    }

    @Test
    public void whenAddItemThenDBHasAnItemWithThatID() {
        this.testLog.info("Test: whenAddItemThenDBHasAnItemWithThatID() started.");
        Item item = this.sql.add(new Item("New request", "New description"));
        assertThat(Integer.parseInt(item.getId()), greaterThan(0));
        Item requested = this.sql.findById(item.getId());
        assertThat(item.getId(), is(requested.getId()));
        this.testLog.info("Test: whenAddItemThenDBHasAnItemWithThatID() ended.");
    }

    @Test
    public void whenDeleteItemThanDBHasNotItemWithThatID() {
        this.testLog.info("Test: whenDeleteItemThanDBHasNotItemWithThatID() started.");
        this.initializeDBWithScript();
        Item item = this.sql.add(new Item("New request", "New description"));
        Item requested = this.sql.findById(item.getId());
        assertThat(item.getId(), is(requested.getId()));
        assertThat(this.sql.delete(requested.getId()), is(true));
        assertThat(this.sql.delete(requested.getId()), is(false));
        assertThat(this.sql.findById(item.getId()), is((Item) null));
        this.testLog.info("Test: whenDeleteItemThanDBHasNotItemWithThatID() ended.");
    }

    @Test
    public void whenUpdateItemThanDBHasUpdatedItemWithThatID() {
        this.testLog.info("Test: whenUpdateItemThanDBHasUpdatedItemWithThatID() started.");
        this.initializeDBWithScript(); // 3 items named '%test%', 2 items named '%request%'
        ArrayList<Item> requested = this.sql.findByName("test");
        assertThat(requested.size(), is(3)); // 3 items named '%test%'
        assertThat(this.sql.replace(requested.get(0).getId(), new Item("New request", "New description")),
                is(true));
        ArrayList<Item> updRequested = this.sql.findByName("test");
        assertThat(updRequested.size(), is(2)); // 2 items named %test% left
        assertThat(this.sql.replace(requested.get(1).getId(), new Item("New request", "New description")),
                is(true));
        updRequested = this.sql.findByName("test");
        assertThat(updRequested.size(), is(1)); // 1 items named %test% left
        assertThat(this.sql.replace(requested.get(2).getId(), new Item("New request", "New description")),
                is(true));
        updRequested = this.sql.findByName("test");
        assertThat(updRequested.size(), is(0)); // no items named %test% left
        updRequested = this.sql.findByName("request");
        assertThat(updRequested.size(), is(5)); // 5 items named %request%
        this.testLog.info("Test: whenUpdateItemThanDBHasUpdatedItemWithThatID() ended.");
    }

    @Test
    public void whenIsStructureThanTrue() {
        assertThat(this.sql.isStructure(), is(true));
    }

    private void initializeDBWithScript() {
        Connection connection;
        try {
            InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties");
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            this.testLog.info("Connected to initialize database.");
        } catch (Exception e) {
            this.testLog.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }

        String aSQLScriptFilePath = "insert.sql";
        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection, false, false);
            Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
            scriptRunner.runScript(reader);
        } catch (Exception e) {
            this.testLog.error(e.getMessage(), e);
        }

        if (connection != null) {
            try {
                connection.close();
                this.testLog.info("Closing connection.");
            } catch (SQLException e) {
                this.testLog.error(e.getMessage(), e);
            }
        }
        this.testLog.info("Initialize connection closed.");
    }
}