package ru.job4j.servlets;

import ru.job4j.servlets.dao.UserDao;
import ru.job4j.servlets.dao.exception.*;
import ru.job4j.servlets.dao.impl.UserDaoDb;
import ru.job4j.servlets.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Validate service for user servlet.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 13/02/2019
 */
public class ValidateService {
    /**
     * The singleton instance of a validate service.
     */
    private static ValidateService singletonValidateServiceInstance = new ValidateService();

    /**
     * The storage singleton instance.
     */
    private final UserDao userDao = UserDaoDb.getDBStoreInstance();

    /**
     * Default constructor.
     */
    private ValidateService() {
    }

    /**
     * Eager initialization of a validate service.
     *
     * @return the singleton instance of a validate service.
     */
    public static ValidateService getSingletonValidateServiceInstance() {
        return ValidateService.singletonValidateServiceInstance;
    }

    /**
     * Checks if the specified user is already exists in the storage. If not, then puts it into storage.
     *
     * @param user - the specified user.
     * @throws DaoSystemException - generates from SQLException.
     * @throws NullArgumentException if the specified user is null.
     * @throws AlreadyExistsModelWithSuchIdException if there already is another user with such id in database.
     */
    public void add(User user) throws DaoSystemException, AlreadyExistsModelWithSuchIdException, NullArgumentException {
        this.checkUser(user);
        if (!this.userDao.containsKey(user)) {
            this.userDao.add(user);
        } else {
            throw new AlreadyExistsModelWithSuchIdException(String.format("User with such id already exists in database: %d.", user.getId()));
        }
    }

    /**
     * Checks if the specified user is in the storage. If it is, then updates it.
     *
     * @param user - the specified user.
     * @return true if successful.
     * @throws DaoSystemException - generates from SQLException.
     * @throws NullArgumentException if the specified user is null.
     */
    public boolean update(User user) throws DaoSystemException, NullArgumentException {
        this.checkUser(user);
        boolean result = false;
        if (this.userDao.containsKey(user)) {
            this.userDao.update(user);
            result = true;
        }
        return result;
    }

    /**
     * Checks if the specified user is in the storage. If it is, then deletes it.
     *
     * @param user - the specified user.
     * @return true if successful.
     * @throws DaoSystemException - generates from SQLException.
     * @throws NullArgumentException if the specified user is null.
     */
    public boolean delete(User user) throws DaoSystemException, NullArgumentException {
        this.checkUser(user);
        boolean result = false;
        if (this.userDao.containsKey(user)) {
            this.userDao.delete(user);
            result = true;
        }
        return result;
    }

    /**
     * Gets all users from the container.
     *
     * @return a collection of all users.
     * @throws DaoSystemException - generates from SQLException.
     */
    public Collection<User> findAll() throws DaoSystemException {
        return (List<User>) this.userDao.findAll();
    }

    /**
     * Checks if the user with the specified id is in the storage. If it is, then gets it.
     *
     * @param id - the specified id.
     * @return a user which is mapped to the specified id. Never return null.
     * @throws DaoSystemException - generates from SQLException.
     * @throws NoSuchModelException - generates if there is no user with such id.
     */
    public User findById(int id) throws DaoSystemException, NoSuchModelException {
        User result = null;
        if (this.userDao.containsKey(id)) {
            result = this.userDao.findById(id);
        }
        if (result == null) {
            throw new NoSuchModelException(String.format("No user with id: %d", id));
        }
        return result;
    }

    /**
     * If specified user is null throws DaoBusiness exception.
     *
     * @param user - the specified exception.
     * @throws NullArgumentException if the specified user is null.
     */
    private void checkUser(User user) throws NullArgumentException {
        if (user == null) {
            throw new NullArgumentException("Null user argument.");
        }
    }
}