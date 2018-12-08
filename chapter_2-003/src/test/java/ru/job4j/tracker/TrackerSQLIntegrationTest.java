package ru.job4j.tracker;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * The integration test class for TrackerSQL.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/12/2018
 */
public class TrackerSQLIntegrationTest {
    private final Logger testLog = LogManager.getLogger(TrackerSQLIntegrationTest.class);

    public Connection initIntegration() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenIsStructureThanTrue() throws SQLException, IOException {
        this.testLog.info("Test \'whenIsStructureThanTrue\' started.");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.initIntegration()))) {
            assertThat(tracker.isStructure(), is(true));
        }
        this.testLog.info("Test \'whenIsStructureThanTrue\' ended.");
    }

    @Test
    public void whenCreateOneNewItemThenDBHasOneElement() throws SQLException, IOException {
        this.testLog.info("Test \'whenCreateOneNewItemThenDBHasOneElement\' started.");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.initIntegration()))) {
            tracker.emptyDataBase();
            this.fillDB(tracker);
            tracker.add(new Item("new name", "new description"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
        this.testLog.info("Test \'whenCreateOneNewItemThenDBHasOneElement\' ended.");
    }

    @Test
    public void whenAddsItemWithWrongAuthorThenFalse() throws SQLException, IOException {
        this.testLog.info("Test \'whenAddsItemWithWrongAuthorThenFalse\' started.");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.initIntegration()))) {
            tracker.emptyDataBase();
            boolean result = false;
            Item item = tracker.add(new Item("a", "test", "test description"));
            if (!item.getId().equals("-1")) {
                result = true;
            }
            assertThat(result, is(false));
        }
        this.testLog.info("Test \'whenAddsItemWithWrongAuthorThenFalse\' ended.");
    }

    @Test
    public void whenAddsItemWithWrightAuthorThenTrue() throws SQLException, IOException {
        this.testLog.info("Test \'whenAddsItemWithWrightAuthorThenTrue\' started.");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.initIntegration()))) {
            tracker.emptyDataBase();
            boolean result = false;
            this.fillDB(tracker);
            Item item = tracker.add(new Item("gsmirnov", "test", "test description"));
            if (!item.getId().equals("-1")) {
                result = true;
            }
            assertThat(result, is(true));
        }
        this.testLog.info("Test \'whenAddsItemWithWrightAuthorThenTrue\' ended.");
    }

    @Test
    public void whenNoItemsThenTrue() throws SQLException, IOException {
        this.testLog.info("Test \'whenNoItemsThenTrue\' started.");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.initIntegration()))) {
            tracker.emptyDataBase();
            this.fillDB(tracker);
            tracker.add(new Item("gsmirnov", "test", "test description")); // items is not empty
            tracker.emptyDataBase(); // db is empty
            assertThat(tracker.findAll().size(), is(0));
        }
        this.testLog.info("Test \'whenNoItemsThenTrue\' ended.");
    }

    @Test
    public void whenDeleteItemThanDBHasNotItemWithThatID() throws SQLException, IOException {
        this.testLog.info("Test \'whenDeleteItemThanDBHasNotItemWithThatID\' started.");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.initIntegration()))) {
            tracker.emptyDataBase();
            this.fillDB(tracker);
            Item item = tracker.add(new Item("New request", "New description"));
            Item requested = tracker.findById(item.getId());
            assertThat(item.getId(), is(requested.getId()));
            assertThat(tracker.delete(requested.getId()), is(true));
            assertThat(tracker.delete(requested.getId()), is(false));
            assertThat(tracker.findById(item.getId()), is((Item) null));
        }
        this.testLog.info("Test \'whenDeleteItemThanDBHasNotItemWithThatID\' ended.");
    }

    @Test
    public void whenUpdateItemThanDBHasUpdatedItemWithThatID() throws SQLException, IOException {
        this.testLog.info("Test \'whenUpdateItemThanDBHasUpdatedItemWithThatID\' started.");
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.initIntegration()))) {
            tracker.emptyDataBase();
            ArrayList<Item> all = tracker.findAll();
            for (Item item : all) {
                System.out.println(item);
            }
            this.fillDB(tracker);
            // 3 items named '%test%', 2 items named '%request%'
            tracker.add(new Item("gsmirnov", "test", "test description"));
            tracker.add(new Item("akaleganov", "test2", "test description2"));
            tracker.add(new Item("guest", "test3", "test description3"));
            tracker.add(new Item("guest", "request1", "request description1"));
            tracker.add(new Item("gsmirnov", "request2", "request description2"));
            ArrayList<Item> requested = tracker.findByName("test");
            assertThat(tracker.replace(requested.get(0).getId(), new Item("New request", "New description")),
                    is(true));
            ArrayList<Item> updRequested = tracker.findByName("test");
            assertThat(updRequested.size(), is(2)); // 2 items named %test% left
            assertThat(tracker.replace(requested.get(1).getId(), new Item("New request", "New description")),
                    is(true));
            updRequested = tracker.findByName("test");
            assertThat(updRequested.size(), is(1)); // 1 items named %test% left
            assertThat(tracker.replace(requested.get(2).getId(), new Item("New request", "New description")),
                    is(true));
            updRequested = tracker.findByName("test");
            assertThat(updRequested.size(), is(0)); // no items named %test% left
            updRequested = tracker.findByName("request");
            assertThat(updRequested.size(), is(5)); // 5 items named %request%
        }
        this.testLog.info("Test \'whenUpdateItemThanDBHasUpdatedItemWithThatID\' ended.");
    }

    public void fillDB(TrackerSQL tracker) {
        tracker.addRole("admin");
        tracker.addRole("user");
        tracker.addUser(new User("gsmirnov", "123", "admin"));
        tracker.addUser(new User("akaleganov", "123", "user"));
        tracker.addUser(new User("guest", "123", "user"));
        tracker.addCategory("normal");
        tracker.addCategory("urgent");
        tracker.addCategory("critical");
        tracker.addCategory("default");
        tracker.addState("accepted");
        tracker.addState("analyze");
        tracker.addState("in work");
        tracker.addState("done");
        tracker.addState("new");
    }
}