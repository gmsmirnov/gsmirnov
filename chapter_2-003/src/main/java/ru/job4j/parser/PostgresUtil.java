package ru.job4j.parser;

import com.ibatis.common.jdbc.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Class for working with PostgresSQL.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/12/2018
 */
public class PostgresUtil implements AutoCloseable {
    /**
     * The logger for PostgresUtil. It used for storing the working log of this util.
     */
    private final Logger parserLog = LogManager.getLogger(PostgresUtil.class);

    /**
     * The connection to PostgreSQL database.
     */
    private final Connection connection;

    /**
     * The constructor with outer connection.
     *
     * @param connection - outer connection.
     */
    public PostgresUtil(Connection connection) {
        this.connection = connection;
        if (!this.isStructure()) {
            this.createStructure();
        }
    }

    /**
     * The constructor which creates and initialises the connection to PostgreSQL database.
     */
    public PostgresUtil(String property) {
        try {
            InputStream in = PostgresUtil.class.getClassLoader().getResourceAsStream(property);
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("jdbc.driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password")
            );
            this.parserLog.info("Connected to database.");
            if (!this.isStructure()) {
                this.createStructure();
            }
        } catch (Exception e) {
            this.parserLog.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * Checks the connection to database.
     *
     * @return true if connected.
     */
    public boolean isConnected() {
        return this.connection != null;
    }

    /**
     * Checks database structure.
     *
     * @return true if the structure exists.
     */
    public boolean isStructure() {
        boolean result = false;
        ArrayList<String> tables = new ArrayList<String>();
        try (PreparedStatement statement = this.connection.prepareStatement(
                "select table_name from information_schema.tables "
                        + "where table_schema='public' order by table_name;")) {
            try (ResultSet rslSet = statement.executeQuery()) {
                while (rslSet.next()) {
                    tables.add(rslSet.getString("table_name"));
                }
            }
            if (tables.contains("vacancy") && tables.contains("last_update")) {
                result = true;
                this.parserLog.info("Database contains tables structure.");
            }
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Creates tables structure if it not exists.
     */
    private void createStructure() {
        String aSQLScriptFilePath = "vacancy_create.sql";
        try {
            ScriptRunner scriptRunner = new ScriptRunner(this.connection, false, false);
            Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
            scriptRunner.runScript(reader);
        } catch (Exception e) {
            this.parserLog.error(e.getMessage(), e);
        }
        this.parserLog.info("Structure created.");
    }

    /**
     * Adds a new vacancy to database.
     *
     * @param vacancy - a new vacancy.
     * @return new vacancy's id in database.
     */
    public int addVacancy(Vacancy vacancy) {
        String id = Constants.WRONG_ID_STRING;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into vacancy(name, text, link) values(?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getDescription());
            statement.setString(3, vacancy.getLink());
            statement.executeUpdate();
            try (ResultSet rslSet = statement.getGeneratedKeys()) {
                if (rslSet.next()) {
                    id = Integer.toString(rslSet.getInt(Constants.ID_LABEL));
                    this.parserLog.info(String.format("An item added to database successful, an item id is %s.", id));
                }
            }
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
        if (id.equals(Constants.WRONG_ID_STRING)) {
            this.parserLog.error("Something going wrong, the items's id is '-1'.");
        }
        return Integer.parseInt(id);
    }

    /**
     * Requests DB for a vacancy with the specified ID.
     *
     * @param id - the specified ID.
     * @return vacancy if exists, null either.
     */
    public Vacancy findVacancy(int id) {
        Vacancy result = null;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "select name, text, link from vacancy where id = ?;")) {
            statement.setInt(1, id);
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = new Vacancy(
                            rslSet.getString("name"),
                            rslSet.getString("text"),
                            rslSet.getString("link"));
                }
            }
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
        if (result == null) {
            this.parserLog.error(String.format("No vacancy with id:%d in database.%n", id));
        }
        return result;
    }

    /**
     * Checks DB for containing the specified vacancy.
     *
     * @param vacancy - the specified vacancy.
     * @return true if containing, false either.
     */
    public boolean isVacancy(Vacancy vacancy) {
        boolean result = false;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "select from vacancy where vacancy.name = ?;")) {
            statement.setString(1, vacancy.getName());
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Inserts the specified collection of vacancies to DB. The specified collection cheks for containing before insert.
     *
     * @param vacancies - the specified collection of vacancies.
     */
    public void addAllVacancies(Collection<Vacancy> vacancies) {
        vacancies = this.filterCollection(vacancies);
        this.parserLog.info(String.format("Ready to update: %s vacancies.%n", vacancies.size()));
        try {
            this.connection.setAutoCommit(false);
            for (Vacancy vacancy : vacancies) {
                this.addVacancy(vacancy);
            }
            this.connection.commit();
            this.connection.setAutoCommit(true);
            this.parserLog.info("DB updated.");
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
    }

    /**
     * Filters the specified collection of vacancies. All containing vacancies throws away. From not containing
     * vacancies it creates a new collection, which returns.
     *
     * @param input - the specified collection of vacancies.
     * @return a filtered collection of vacancies, without vacancies, already containing in DB.
     */
    private Collection<Vacancy> filterCollection(Collection<Vacancy> input) {
        Collection<Vacancy> output = new LinkedList<Vacancy>();
        for (Vacancy vacancy : input) {
            if (!this.isVacancy(vacancy)) {
                output.add(vacancy);
            }
        }
        return output;
    }

    /**
     * Gets the collection of all vacancies from DB.
     *
     * @return - a collection of all vacancies from DB.
     */
    public Collection<Vacancy> getAllVacancies() {
        Collection<Vacancy> vacancies = new LinkedList<Vacancy>();
        try (PreparedStatement statement = this.connection.prepareStatement("select * from vacancy;")) {
            try (ResultSet rslSet = statement.executeQuery()) {
               while (rslSet.next()) {
                   vacancies.add(new Vacancy(
                           rslSet.getString("name"),
                           rslSet.getString("text"),
                           rslSet.getString("link")
                   ));
               }
            }
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
        return vacancies;
    }

    /**
     * Sets the last update date to database.
     *
     * @param lastUpdate - the specified date.
     */
    private void setLastUpdate(java.util.Date lastUpdate) {
        try (PreparedStatement statement = this.connection.prepareStatement("insert into last_update(creation_date) values(?)")) {
            statement.setTimestamp(1, new Timestamp(lastUpdate.getTime()));
            statement.executeUpdate();
            this.parserLog.info(String.format("Last update is updated to: %s.%n", lastUpdate));
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
    }

    /**
     * Gets the last update date from database if it is not empty. If it is empty the date sets as current time - one year.
     *
     * @return the date of last update.
     */
    public java.util.Date getLastUpdate() {
        Date result = null;
        try (PreparedStatement statement = this.connection.prepareStatement("select * from last_update")) {
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = new Date(rslSet.getTimestamp("creation_date").getTime());
                    this.parserLog.info(String.format("Date in last_update is: %s.%n", result));
                } else {
                    result = new Date(System.currentTimeMillis() - Constants.YEAR);
                    this.parserLog.info(String.format("No date in last_update table. Date set: %s.%n", result));
                }
            }
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Updates the last update date in database.
     *
     * @param lastUpdate - the newest last update date.
     */
    public void updateLastUpdate(java.util.Date lastUpdate) {
        this.emptyLastUpdate();
        this.setLastUpdate(lastUpdate);
    }

    /**
     * Clears database, empties all tables.
     */
    public void emptyDataBase() {
        this.emptyLastUpdate();
        this.emptyVacancies();
    }

    /**
     * Empties vacancies table.
     */
    private void emptyVacancies() {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from vacancy;")) {
            statement.executeUpdate();
            this.parserLog.info(String.format("Table \'Vacancy\' was cleared."));
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
    }

    /**
     * Empties last update table.
     */
    private void emptyLastUpdate() {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from last_update;")) {
            statement.executeUpdate();
            this.parserLog.info(String.format("Table \'last_update\' was cleared."));
        } catch (SQLException e) {
            this.parserLog.error(e.getMessage(), e);
        }
    }

    /**
     * Closes the connection to PostgreSQL database.
     *
     * @throws Exception autocloseable format.
     */
    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.parserLog.info("Closing connection.");
            } catch (SQLException e) {
                this.parserLog.error(e.getMessage(), e);
                throw new Exception(e);
            }
        }
    }
}