package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * The test class for TrackerSQL.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 18/10/2018
 */
public class TrackerSQLTest {
    private TrackerSQL sql;
    private Logger testLog;

    @Before
    public void init() {
        this.testLog = LogManager.getLogger(TrackerSQLTest.class);
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
        this.testLog.info("Test \'whenConnectedThenTrue()\' started.");
        assertThat(this.sql.isConnected(), is(true));
        this.testLog.info("Test \'whenConnectedThenTrue()\' ended.");
    }

    @Test
    public void whenIsStructureThanTrue() {
        this.testLog.info("Test \'whenIsStructureThanTrue\' started.");
        assertThat(this.sql.isStructure(), is(true));
        this.testLog.info("Test \'whenIsStructureThanTrue\' ended.");
    }

    @Test
    public void whenAddsItemWithWrongAuthorThenFalse() {
        this.testLog.info("Test \'whenAddsItemWithWrongAuthorThenFalse\' started.");
        boolean result = false;
        Item item = this.sql.add(new Item("a", "test", "test description"));
        if (!item.getId().equals("-1")) {
            result = true;
        }
        assertThat(result, is(false));
        this.testLog.info("Test \'whenAddsItemWithWrongAuthorThenFalse\' ended.");
    }

    @Test
    public void whenAddsItemWithWrightAuthorThenTrue() {
        this.testLog.info("Test \'whenAddsItemWithWrightAuthorThenTrue\' started.");
        boolean result = false;
        this.sql.emptyDataBase(); // db is empty
        this.fillDB();
        Item item = this.sql.add(new Item("gsmirnov", "test", "test description"));
        if (!item.getId().equals("-1")) {
            result = true;
        }
        assertThat(result, is(true));
        this.testLog.info("Test \'whenAddsItemWithWrightAuthorThenTrue\' ended.");
    }

    @Test
    public void whenNoItemsThenTrue() {
        this.testLog.info("Test \'whenNoItemsThenTrue\' started.");
        this.sql.emptyDataBase(); // db is empty
        this.fillDB();
        this.sql.add(new Item("gsmirnov", "test", "test description")); // items is not empty
        this.sql.emptyDataBase(); // db is empty
        assertThat(this.sql.findAll().size(), is(0));
        this.testLog.info("Test \'whenNoItemsThenTrue\' ended.");
    }

    @Test
    public void whenAddItemThenDBHasAnItemWithThatID() {
        this.testLog.info("Test \'whenAddItemThenDBHasAnItemWithThatID\' started.");
        this.sql.emptyDataBase(); // db is empty
        this.fillDB();
        Item item = this.sql.add(new Item("New request", "New description"));
        assertThat(Integer.parseInt(item.getId()), greaterThan(0));
        Item requested = this.sql.findById(item.getId());
        assertThat(item.getId(), is(requested.getId()));
        this.testLog.info("Test \'whenAddItemThenDBHasAnItemWithThatID\' ended.");
    }

    @Test
    public void whenDeleteItemThanDBHasNotItemWithThatID() {
        this.testLog.info("Test \'whenDeleteItemThanDBHasNotItemWithThatID\' started.");
        this.sql.emptyDataBase(); // db is empty
        this.fillDB();
        Item item = this.sql.add(new Item("New request", "New description"));
        Item requested = this.sql.findById(item.getId());
        assertThat(item.getId(), is(requested.getId()));
        assertThat(this.sql.delete(requested.getId()), is(true));
        assertThat(this.sql.delete(requested.getId()), is(false));
        assertThat(this.sql.findById(item.getId()), is((Item) null));
        this.testLog.info("Test \'whenDeleteItemThanDBHasNotItemWithThatID\' ended.");
    }

    @Test
    public void whenUpdateItemThanDBHasUpdatedItemWithThatID() {
        this.testLog.info("Test \'whenUpdateItemThanDBHasUpdatedItemWithThatID\' started.");
        this.sql.emptyDataBase(); // db is empty
        this.fillDB();
        // 3 items named '%test%', 2 items named '%request%'
        this.sql.add(new Item("gsmirnov", "test", "test description"));
        this.sql.add(new Item("akaleganov", "test2", "test description2"));
        this.sql.add(new Item("guest", "test3", "test description3"));
        this.sql.add(new Item("guest", "request1", "request description1"));
        this.sql.add(new Item("gsmirnov", "request2", "request description2"));
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
        this.testLog.info("Test \'whenUpdateItemThanDBHasUpdatedItemWithThatID\' ended.");
    }

    public void fillDB() {
        this.sql.addRole("admin");
        this.sql.addRole("user");
        this.sql.addUser(new User("gsmirnov", "123", "admin"));
        this.sql.addUser(new User("akaleganov", "123", "user"));
        this.sql.addUser(new User("guest", "123", "user"));
        this.sql.addCategory("normal");
        this.sql.addCategory("urgent");
        this.sql.addCategory("critical");
        this.sql.addCategory("default");
        this.sql.addState("accepted");
        this.sql.addState("analyze");
        this.sql.addState("in work");
        this.sql.addState("done");
        this.sql.addState("new");
    }
}