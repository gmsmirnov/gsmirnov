package ru.job4j.servlets.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.dao.UserDao;
import ru.job4j.servlets.dao.exception.AlreadyExistsModelWithSuchIdException;
import ru.job4j.servlets.dao.exception.DaoBusinessException;
import ru.job4j.servlets.dao.exception.DaoSystemException;
import ru.job4j.servlets.dao.exception.NoSuchModelException;
import ru.job4j.servlets.model.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a Postrgres database storage.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 21/02/2019
 */
public class UserDaoDb implements UserDao {
    /**
     * The connections' pool.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * The singleton instance of the storage.
     */
    private static final UserDaoDb INSTANCE = new UserDaoDb();

    /**
     * Default constructor.
     */
    public UserDaoDb() {
        UserDaoDb.SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/users");
        UserDaoDb.SOURCE.setUsername("postgres");
        UserDaoDb.SOURCE.setPassword("postgres");
        UserDaoDb.SOURCE.setDriverClassName("org.postgresql.Driver");
        UserDaoDb.SOURCE.setMinIdle(5);
        UserDaoDb.SOURCE.setMaxIdle(10);
        UserDaoDb.SOURCE.setMaxOpenPreparedStatements(100);
    }

    /**
     * Eager initialization of the storage singleton instance.
     *
     * @return the singleton instance of the storage.
     */
    public static UserDaoDb getDBStoreInstance() {
        return UserDaoDb.INSTANCE;
    }

    /**
     * Puts the specified value into the storage.
     *
     * @param user - the specified value.
     * @throws DaoSystemException if SQLException occurs.
     */
    @Override
    public void add(User user) throws DaoSystemException {
        try (Connection connection = UserDaoDb.SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into users(id, name, login, email) values(?, ?, ?, ?);")) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoSystemException(e.getMessage(), e);
        }
    }

    /**
     * Replaces the specified value in the storage.
     *
     * @param user - the specified value.
     * @throws DaoSystemException if SQLException occurs.
     */
    @Override
    public void update(User user) throws DaoSystemException {
        try (Connection connection = UserDaoDb.SOURCE.getConnection()) {
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
            throw new DaoSystemException(e.getMessage(), e);
        }
    }

    /**
     * Deletes the specified user in the storage.
     *
     * @param user - the specified user.
     * @throws DaoSystemException if SQLException occurs.
     */
    @Override
    public void delete(User user) throws DaoSystemException {
        try (Connection connection = UserDaoDb.SOURCE.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from users where id = ?;"
            );
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoSystemException(e.getMessage(), e);
        }
    }

    /**
     * Gets a collection of all users in the storage.
     *
     * @return a collection of all users in the storage. Never return null.
     * @throws DaoSystemException if SQLException occurs.
     */
    @Override
    public Collection<User> findAll() throws DaoSystemException {
        List<User> result = new LinkedList<User>();
        try (Connection connection = UserDaoDb.SOURCE.getConnection()) {
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
            throw new DaoSystemException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Finds the user in the store by the specified id.
     *
     * @param id - the specified id.
     * @return the user which is mapped to the specified id. Never return null.
     * @throws DaoSystemException - generates from SQLException.
     */
    @Override
    public User findById(int id) throws DaoSystemException {
        return this.tryGetUserFromDb(id);
    }

    /**
     * Checks if the specified user is in the container.
     *
     * @param user - the specified user.
     * @return true if the specified user exists in the container.
     * @throws DaoSystemException if SQLException occurs.l.
     */
    @Override
    public boolean contains(User user) throws DaoSystemException {
        boolean result = false;
        if (user.equals(this.tryGetUserFromDb(user.getId()))) {
            result = true;
        }
        return result;
    }

    /**
     * Checks if the specified user's key is used in the storage.
     *
     * @param user - the specified user, which id checks.
     * @return true if the user's id is used like a key.
     * @throws DaoSystemException if SQLException occurs.
     */
    @Override
    public boolean containsKey(User user) throws DaoSystemException {
        return this.tryGetUserFromDb(user.getId()) != null;
    }

    /**
     * Checks if the specified id is used in the storage.
     *
     * @param id - the specified id.
     * @return true if the id is used like a key.
     * @throws DaoSystemException if SQLException occurs.
     */
    @Override
    public boolean containsKey(int id) throws DaoSystemException {
        return this.tryGetUserFromDb(id) != null;
    }

    /**
     * Tries to get the user with the specified id from database.
     *
     * @param id - the specified user's id.
     * @return the user with the specified id or null.
     * @throws DaoSystemException if SQLException occurs.
     */
    private User tryGetUserFromDb(int id) throws DaoSystemException {
        User result = null;
        try (Connection connection = UserDaoDb.SOURCE.getConnection()) {
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
            throw new DaoSystemException(e.getMessage(), e);
        }
        return result;
    }
}