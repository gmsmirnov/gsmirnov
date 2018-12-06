package ru.job4j.sqlite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class StoreSQL - work with SQLite.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/11/2018
 */
public class StoreSQL implements Closeable {
    /**
     * The logger for SQLite. It used for storing the working with database.
     */
    private final Logger sqLiteLog = LogManager.getLogger(StoreSQL.class);

    /**
     * The connection to database.
     */
    private final Connection connection;

    /**
     * The constructor which creates and initialises the connection to SQLite database.
     *
     * @param config - configuration of connection.
     */
    public StoreSQL(Config config) {
        try {
            this.connection = DriverManager.getConnection(config.get("url"));
            if (this.connection != null) {
                DatabaseMetaData meta = this.connection.getMetaData();
                this.sqLiteLog.info(String.format("The driver name is: %s", meta.getDriverName()));
                this.sqLiteLog.info("Connection to SQLite has been established.");
            }
        } catch (Exception e) {
            this.sqLiteLog.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * The initializer which initialize database.
     *
     * @param quantity - the quantity of initialize numbers.
     */
    public void init(int quantity) {
        if (!this.isStructure()) {
            this.createTable();
        }
        this.emptyTable();
        this.fillTable(quantity);
    }

    /**
     * Checks database 'magnit'.
     *
     * @return true if the structure exists.
     */
    public boolean isStructure() {
        boolean result = false;
        ArrayList<String> tables = new ArrayList<String>();
        try (PreparedStatement statement = this.connection.prepareStatement(
                "select name from sqlite_master where type='table' order by name;")) {
            try (ResultSet rslSet = statement.executeQuery()) {
                while (rslSet.next()) {
                    tables.add(rslSet.getString("name"));
                }
            }
            if (tables.contains("entry")) {
                result = true;
                this.sqLiteLog.info("Database contains table 'entry'.");
            }
        } catch (SQLException e) {
            this.sqLiteLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Deletes all rows from the table.
     */
    private void emptyTable() {
        String sql = "delete from entry;";
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            this.sqLiteLog.error(e.getMessage(), e);
        }
    }

    /**
     * Checks if entry-table is empty.
     *
     * @return true if empty.
     */
    public boolean isTableEmpty() {
        return this.findAll().size() == 0;
    }

    /**
     * Deletes entry-table.
     */
    public void deleteTable() {
        String sql = "drop table entry;";
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            this.sqLiteLog.error(e.getMessage(), e);
        }
    }

    /**
     * Creates new table if it not exists.
     */
    private void createTable() {
        String sql = "create table if not exists entry( "
                + "id integer primary key, "
                + "number integer"
                + ");";
        try (Statement statement = this.connection.createStatement()) {
            statement.executeQuery(sql);
            this.sqLiteLog.info("Table \'entry\' has been created.");
        } catch (SQLException e) {
            this.sqLiteLog.error(e.getMessage(), e);
        }
    }

    /**
     * Fills table with initial numbers.
     *
     * @param quantity - initial numbers, from 1 to "quantity".
     */
    private void fillTable(int quantity) {
        try {
            this.connection.setAutoCommit(false);
            for (int counter = 1; counter <= quantity; counter++) {
                try (PreparedStatement statement = this.connection.prepareStatement(
                        "insert into entry(number) values(?);",
                        Statement.RETURN_GENERATED_KEYS
                )) {
                    statement.setInt(1, counter);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    this.sqLiteLog.error(e.getMessage(), e);
                }
            }
            this.connection.commit();
            this.sqLiteLog.info(String.format("%d items has been added to table \'entry\'.", quantity));
        } catch (SQLException e) {
            this.sqLiteLog.error(e.getMessage(), e);
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                this.sqLiteLog.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     *  Finds all numbers from database.
     *
     * @return the list of all numbers.
     */
    public List<StoreXML.Field> findAll() {
        List<StoreXML.Field> result = new LinkedList<StoreXML.Field>();
        try (Statement statement = this.connection.createStatement()) {
            try (ResultSet rslSet = statement.executeQuery("select * from entry;")) {
                while (rslSet.next()) {
                    result.add(new StoreXML.Field(rslSet.getInt("number")));
                }
                this.sqLiteLog.info(String.format("\'Find all\' successful, found %d items.", result.size()));
            }
        } catch (SQLException e) {
            this.sqLiteLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Clears the entry table.
     */
    public void clearEntryTable() {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from entry;")) {
            statement.executeUpdate();
            this.sqLiteLog.info("Entry table was cleared.");
        } catch (SQLException e) {
            this.sqLiteLog.error(e.getMessage(), e);
        }
    }

    /**
     * Closes the connection.
     *
     * @throws IOException if SQLException occurs during close connection operation.
     */
    @Override
    public void close() throws IOException {
        try {
            if (this.connection != null) {
                this.connection.close();
                this.sqLiteLog.info("Connection has been closed.");
            }
        } catch (SQLException sqle) {
            this.sqLiteLog.error(sqle.getMessage(), sqle);
            throw new IOException(sqle);
        }
    }
}