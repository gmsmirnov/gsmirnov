package ru.job4j.tracker;

import com.ibatis.common.jdbc.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class TrackerSQL - class wrapper, that manage requests. Requests stored in PostgreSQL database.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 18/10/2018
 */
public class TrackerSQL implements ITracker, Closeable {
    /**
     * The logger for TrackerSQL. It used for storing the working log of this tracker.
     */
    private final Logger sqlLog = LogManager.getLogger(TrackerSQL.class);

    /**
     * The connection to PostgreSQL database.
     */
    private final Connection connection;

    /**
     * The constructor which creates and initialises the connection to PostgreSQL database.
     */
    public TrackerSQL() {
        try {
            InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties");
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            this.sqlLog.info("Connected to database.");
            this.createStructure();
        } catch (Exception e) {
            this.sqlLog.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
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
            if (tables.contains("attaches") && tables.contains("categories") && tables.contains("comments")
                    && tables.contains("items") && tables.contains("roles") && tables.contains("roles_rules")
                    && tables.contains("rules") && tables.contains("states") && tables.contains("users")) {
                result = true;
                this.sqlLog.info("Database contains tables structure.");
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Creates tables structure if it not exists.
     */
    private void createStructure() {
        String aSQLScriptFilePath = "createTables.sql";
        try {
            ScriptRunner scriptRunner = new ScriptRunner(this.connection, false, false);
            Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
            scriptRunner.runScript(reader);
        } catch (Exception e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        this.sqlLog.info("Structure created.");
    }

    /**
     * Checks the connection to PostgreSQL database.
     *
     * @return true if connected.
     */
    public boolean isConnected() {
        return this.connection != null;
    }

    /**
     * Addition request to container.
     *
     * @param item - new request that adding to container.
     * @return item with its id in container.
     */
    @Override
    public Item add(Item item) {
        String id = Constants.WRONG_ID_STRING;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into items(author, title, description, creation_date, state, category) values(?, ?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, this.selectAuthorId(item));
            statement.setString(2, item.getName());
            statement.setString(3, item.getDesc());
            statement.setTimestamp(4, new Timestamp(item.getCreation()));
            statement.setInt(5, this.selectStateId(item));
            statement.setInt(6, this.selectCategoryId(item));
            statement.executeUpdate();
            try (ResultSet rslSet = statement.getGeneratedKeys()) {
                if (rslSet.next()) {
                    id = Integer.toString(rslSet.getInt(Constants.ID_LABEL));
                    this.sqlLog.info(String.format("An item added to database successful, an item id is %s.", id));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        item.setId(id);
        if (item.getId().equals(Constants.WRONG_ID_STRING)) {
            this.sqlLog.error("Something going wrong, the items's id is '-1'.");
        }
        return item;
    }

    /**
     * Replaces one request by another request by id.
     *
     * @param id - the id of replacement request.
     * @param newItem - new request that replaces older request.
     * @return true if request was edited successfully, false if there is no request with such id.
     */
    @Override
    public boolean replace(String id, Item newItem) {
        boolean result = false;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "update items \n"
                        + "set author = ?, title = ?, description = ?, creation_date = ?, state = ?, category = ?\n"
                        + "where items.id = ?;")) {
            statement.setInt(1, this.selectAuthorId(newItem));
            statement.setString(2, newItem.getName());
            statement.setString(3, newItem.getDesc());
            statement.setTimestamp(4, new Timestamp(newItem.getCreation()));
            statement.setInt(5, this.selectStateId(newItem));
            statement.setInt(6, this.selectCategoryId(newItem));
            statement.setInt(7, Integer.parseInt(id));
            int resUpd = statement.executeUpdate();
            if (resUpd > 0) {
                result = true;
                this.sqlLog.info(String.format("Updated %d elements", resUpd));
            } else {
                this.sqlLog.info(String.format("No item with ID: %s.", id));
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Deletes one request by id.
     *
     * @return true if request was delete successfully, false if there is no request with such id.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from items where items.id = ?;")) {
            statement.setInt(1, Integer.parseInt(id));
            if (statement.executeUpdate() > 0) {
                result = true;
                this.sqlLog.info(String.format("Deleted item with ID: %s.", id));
            } else {
                this.sqlLog.info(String.format("No item with ID: %s.", id));
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds all not null requests.
     *
     * @return new container, which contains all not null requests.
     */
    @Override
    public ArrayList<Item> findAll() {
        ArrayList<Item> result = new ArrayList<Item>();
        try (Statement statement = this.connection.createStatement()) {
            try (ResultSet rslSet = statement.executeQuery(
                    "select items.id, users.name, items.title, items.description, items.creation_date, states.state, categories.category from items, users, categories, states\n"
                            + " where items.author = users.id and items.state = states.id and items.category = categories.id;")) {
                while (rslSet.next()) {
                    result.add(new Item(
                            rslSet.getString("id"),
                            rslSet.getString("name"),
                            rslSet.getString("title"),
                            rslSet.getString("description"),
                            rslSet.getTimestamp("creation_date").getTime(),
                            rslSet.getString("state"),
                            rslSet.getString("category")));
                }
                this.sqlLog.info(String.format("\'Find all\' successful, got %d items.", result.size()));
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds all requests by name.
     *
     * @param name - the name of needed requests.
     * @return new container, which contains all requests with specified name.
     */
    @Override
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> result = new ArrayList<Item>();
        try (PreparedStatement statement = this.connection.prepareStatement(
                "select items.id, users.name, items.title, items.description, items.creation_date, states.state, categories.category from items, users, categories, states\n"
                        + " where items.author = users.id and items.state = states.id and items.category = categories.id and items.title like ?;")) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet rslSet = statement.executeQuery()) {
                while (rslSet.next()) {
                    result.add(new Item(
                            rslSet.getString("id"),
                            rslSet.getString("name"),
                            rslSet.getString("title"),
                            rslSet.getString("description"),
                            rslSet.getTimestamp("creation_date").getTime(),
                            rslSet.getString("state"),
                            rslSet.getString("category")));
                }
                this.sqlLog.info(String.format("\'Find by Name\' successful, got %d items.", result.size()));
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds request by id.
     *
     * @param id - the id of needed requests.
     * @return the request with specified id or null if there is no such requests.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "select items.id, users.name, items.title, items.description, items.creation_date, states.state, categories.category from items, users, categories, states\n"
                        + " where items.author = users.id and items.state = states.id and items.category = categories.id and items.id = ?;")) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = new Item(
                            rslSet.getString("id"),
                            rslSet.getString("name"),
                            rslSet.getString("title"),
                            rslSet.getString("description"),
                            rslSet.getTimestamp("creation_date").getTime(),
                            rslSet.getString("state"),
                            rslSet.getString("category"));
                    this.sqlLog.info(String.format("\'Find by ID\' successful, got an item %s with ID: %s.",
                            result.getName(), result.getId()));
                }
                this.sqlLog.info("\'Find by ID\' not found any items.");
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds the id of item's author in 'users' table.
     *
     * @param item - the specified item, which author id needed. Item contains author as String.
     * @return author's id as int.
     */
    private int selectAuthorId(Item item) {
        int result = Constants.WRONG_ID_INT;
        try (PreparedStatement statement = this.connection.prepareStatement("select id from users where users.name = ?")) {
            statement.setString(1, item.getAuthor());
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = rslSet.getInt(Constants.ID_LABEL);
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds the author's name in the 'users' table by author's id.
     *
     * @param id - the author's id in the 'users' table.
     * @return author's name.
     */
    private String selectAuthorName(int id) {
        String result = "";
        try (PreparedStatement statement = this.connection.prepareStatement("select name from users where users.id = ?")) {
            statement.setInt(1, id);
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = rslSet.getString(Constants.NAME_LABEL);
                    this.sqlLog.info(String.format("Got author's id: %d author: %s", id, result));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds the id of item's state in 'states' table.
     *
     * @param item - the specified item, which state id needed. Item contains state as String.
     * @return state's id as int.
     */
    private int selectStateId(Item item) {
        int result = Constants.WRONG_ID_INT;
        try (PreparedStatement statement = this.connection.prepareStatement("select id from states where states.state = ?")) {
            statement.setString(1, item.getState());
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = rslSet.getInt(Constants.ID_LABEL);
                    this.sqlLog.info(String.format("Got state's id: %d state: %s", result, item.getState()));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds the id of item's category in 'categories' table.
     *
     * @param item - the specified item, which category id needed. Item contains category as String.
     * @return category's id as int.
     */
    private int selectCategoryId(Item item) {
        int result = Constants.WRONG_ID_INT;
        try (PreparedStatement statement = this.connection.prepareStatement("select id from categories where category = ?")) {
            statement.setString(1, item.getCategory());
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = rslSet.getInt(Constants.ID_LABEL);
                    this.sqlLog.info(String.format("Got category's id: %d category: %s", result, item.getCategory()));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds the id of user's role in 'roles' table.
     *
     * @param user - the specified user.
     * @return role's id as int.
     */
    private int selectRoleId(User user) {
        int result = Constants.WRONG_ID_INT;
        try (PreparedStatement statement = this.connection.prepareStatement("select id from roles where name = ?")) {
            statement.setString(1, user.getRole());
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = rslSet.getInt(Constants.ID_LABEL);
                    this.sqlLog.info(String.format("Got role's id: %d category: %s", result, user.getRole()));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Adds new role to database.
     *
     * @param role - the name of a new role.
     * @return the id of a new role int roles table.
     */
    public int addRole(String role) {
        String id = Constants.WRONG_ID_STRING;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into roles(name) values(?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role);
            statement.executeUpdate();
            try (ResultSet rslSet = statement.getGeneratedKeys()) {
                if (rslSet.next()) {
                    id = Integer.toString(rslSet.getInt(Constants.ID_LABEL));
                    this.sqlLog.info(String.format("An item added to database successful, a role's id is %s.", id));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        if (id.equals(Constants.WRONG_ID_STRING)) {
            this.sqlLog.error("Something going wrong, the id is '-1'.");
        }
        return Integer.parseInt(id);
    }

    /**
     * Adds new state to database.
     *
     * @param state - the name of a new state.
     * @return the id of a new state in states table.
     */
    public int addState(String state) {
        String id = Constants.WRONG_ID_STRING;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into states(state) values(?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, state);
            statement.executeUpdate();
            try (ResultSet rslSet = statement.getGeneratedKeys()) {
                if (rslSet.next()) {
                    id = Integer.toString(rslSet.getInt(Constants.ID_LABEL));
                    this.sqlLog.info(String.format("An item added to database successful, a state's id is %s.", id));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        if (id.equals(Constants.WRONG_ID_STRING)) {
            this.sqlLog.error("Something going wrong, the id is '-1'.");
        }
        return Integer.parseInt(id);
    }

    /**
     * Adds new category to database.
     *
     * @param category - the name of a new category.
     * @return the id of a new category in categories table.
     */
    public int addCategory(String category) {
        String id = Constants.WRONG_ID_STRING;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into categories(category) values(?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category);
            statement.executeUpdate();
            try (ResultSet rslSet = statement.getGeneratedKeys()) {
                if (rslSet.next()) {
                    id = Integer.toString(rslSet.getInt(Constants.ID_LABEL));
                    this.sqlLog.info(String.format("An item added to database successful, a category's id is %s.", id));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        if (id.equals(Constants.WRONG_ID_STRING)) {
            this.sqlLog.error("Something going wrong, the id is '-1'.");
        }
        return Integer.parseInt(id);
    }

    /**
     * Adds new user to database.
     *
     * @param user - the specified user.
     * @return the new user's id in user's table.
     */
    public int addUser(User user) {
        String id = Constants.WRONG_ID_STRING;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "insert into users(name, password, role) values(?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, this.selectRoleId(user));
            statement.executeUpdate();
            try (ResultSet rslSet = statement.getGeneratedKeys()) {
                if (rslSet.next()) {
                    id = Integer.toString(rslSet.getInt(Constants.ID_LABEL));
                    this.sqlLog.info(String.format("An item added to database successful, an user's id is %s.", id));
                }
            }
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        if (id.equals(Constants.WRONG_ID_STRING)) {
            this.sqlLog.error("Something going wrong, the id is '-1'.");
        }
        return Integer.parseInt(id);
    }

    /**
     * Clears database, empties all tables.
     *
     * @return true if successful.
     */
    public boolean emptyDataBase() {
        boolean result = false;
        try {
            this.emptyItems();
            this.emptyCategories();
            this.emptyStates();
            this.emptyRolesRules();
            this.emptyRules();
            this.emptyUsers();
            this.emptyRoles();
            result = true;
        } catch (SQLException e) {
            this.sqlLog.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Empties items table.
     *
     * @throws SQLException if executeUpdate throws it.
     */
    private void emptyItems() throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from items;")) {
            statement.executeUpdate();
            this.sqlLog.info(String.format("Table \'Items\' was cleared."));
        }
    }

    /**
     * Empties categories table.
     *
     * @throws SQLException if executeUpdate throws it.
     */
    private void emptyCategories() throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from categories;")) {
            statement.executeUpdate();
            this.sqlLog.info(String.format("Table \'Categories\' was cleared."));
        }
    }

    /**
     * Empties states table.
     *
     * @throws SQLException if executeUpdate throws it.
     */
    private void emptyStates() throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from states;")) {
            statement.executeUpdate();
            this.sqlLog.info(String.format("Table \'States\' was cleared."));
        }
    }

    /**
     * Empties roles-rules table.
     *
     * @throws SQLException if executeUpdate throws it.
     */
    private void emptyRolesRules() throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from roles_rules;")) {
            statement.executeUpdate();
            this.sqlLog.info(String.format("Table \'Roles-Rules\' was cleared."));
        }
    }

    /**
     * Empties rules table.
     *
     * @throws SQLException if executeUpdate throws it.
     */
    private void emptyRules() throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from rules;")) {
            statement.executeUpdate();
            this.sqlLog.info(String.format("Table \'Rules\' was cleared."));
        }
    }

    /**
     * Empties users table.
     *
     * @throws SQLException if executeUpdate throws it.
     */
    private void emptyUsers() throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from users;")) {
            statement.executeUpdate();
            this.sqlLog.info(String.format("Table \'Users\' was cleared."));
        }
    }

    /**
     * Empties roles table.
     *
     * @throws SQLException if executeUpdate throws it.
     */
    private void emptyRoles() throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "delete from roles;")) {
            statement.executeUpdate();
            this.sqlLog.info(String.format("Table \'Roles\' was cleared."));
        }
    }

    /**
     * Closes the connection to PostgreSQL database.
     *
     * @throws IOException autocloseable format.
     */
    @Override
    public void close() throws IOException {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.sqlLog.info("Closing connection.");
            } catch (SQLException e) {
                this.sqlLog.error(e.getMessage(), e);
                throw new IOException(e);
            }
        }
    }
}