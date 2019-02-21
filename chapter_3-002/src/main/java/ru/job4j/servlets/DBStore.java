package ru.job4j.servlets;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a Postrgres database storage.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/02/2019
 */
public class DBStore implements Store {
    /**
     * The connections' poll.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * The singleton instance of the storage.
     */
    private static final DBStore INSTANCE = new DBStore();

    /**
     * Default constructor.
     */
    public DBStore() {
        DBStore.SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/users");
        DBStore.SOURCE.setUsername("postgres");
        DBStore.SOURCE.setPassword("postgres");
        DBStore.SOURCE.setDriverClassName("org.postgresql.Driver");
        DBStore.SOURCE.setMinIdle(5);
        DBStore.SOURCE.setMaxIdle(10);
        DBStore.SOURCE.setMaxOpenPreparedStatements(100);
    }

    /**
     * Eager initialization of the storage singleton instance.
     *
     * @return the singleton instance of the storage.
     */
    public static DBStore getDBStoreInstance() {
        return DBStore.INSTANCE;
    }

    /**
     * Puts the specified value into the storage.
     *
     * @param user - the specified value.
     */
    @Override
    public void add(User user) {
        try (Connection connection = DBStore.SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into users(id, name, login, email) values(?, ?, ?, ?);")) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces the specified value in the storage.
     *
     * @param user - the specified value.
     */
    @Override
    public void update(User user) {
        try (Connection connection = DBStore.SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "update users "
                            + "set name = ?, login = ?, email = ?"
                            + "where id = ?"
            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the specified user in the storage.
     *
     * @param user - the specified user.
     */
    @Override
    public void delete(User user) {
        try (Connection connection = DBStore.SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from users where id = ?;"
            );
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a collection of all users in the storage.
     *
     * @return a collection of all users in the storage.
     */
    @Override
    public Collection<User> findAll() {
        List<User> result = new LinkedList<User>();
        try (Connection connection = DBStore.SOURCE.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from users;");
            try (ResultSet rslSet = statement.getResultSet()) {
                while (rslSet.next()) {
                    result.add(new User(
                            String.format("%d", rslSet.getInt("id")),
                            rslSet.getString("name"),
                            rslSet.getString("login"),
                            rslSet.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Finds the user in the store by the specified id.
     *
     * @param id - the specified id.
     * @return the user which is mapped to the specified id.
     */
    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = DBStore.SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select name, login, email from users where id = ?;");
            statement.setInt(1, id);
            try (ResultSet rslSet = statement.executeQuery()) {
                if (rslSet.next()) {
                    result = new User(
                            String.format("%d", id),
                            rslSet.getString("name"),
                            rslSet.getString("login"),
                            rslSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks if the specified user is in the container.
     *
     * @param user - the specified user.
     * @return true if the specified user exists in the container.
     */
    @Override
    public boolean contains(User user) {
        return this.findById(user.getId()) != null;
    }

    /**
     * Checks if the specified user's key is used in the map.
     *
     * @param user - the specified user, which id checks.
     * @return true if the user's id is used like a key.
     */
    @Override
    public boolean containsKey(User user) {
        return this.findById(user.getId()) != null;
    }

    /**
     * Checks if the specified id is used in the map.
     *
     * @param id - the specified id.
     * @return true if the id is used like a key.
     */
    @Override
    public boolean containsKey(int id) {
        return this.findById(id) != null;
    }
}