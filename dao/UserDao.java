package dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import database.Database;
import entity.User;
import exception.DAOException;
import exception.DatabaseException;

public class UserDao implements DAO<User> {
    private Database database = Database.getInstance();

    @Override
    public void save(User entity) throws DAOException {
        try {
            database.save(User.class, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to save user", e);
        }
    }

    @Override
    public Optional<User> findById(int id) throws DAOException {
        try {
            return database.findById(User.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find a user by id", e);
        }
    }

    @Override
    public List<User> findAll() throws DAOException {
        try {
            return database.findAll(User.class);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all users", e);
        }
    }

    @Override
    public List<User> findAll(Predicate<User> filter) throws DAOException {
        try {
            return database.findAll(User.class).stream().filter(filter).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all filtered users", e);
        }
    }

    @Override
    public List<User> findAll(Comparator<User> comparator) throws DAOException {
        try {
            return database.findAll(User.class).stream().sorted(comparator).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all comparated users", e);
        }
    }

    @Override
    public void update(int id, User entity) throws DAOException {
        try {
            database.update(User.class, id, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to update user", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            database.delete(User.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to delete user", e);
        }
    }
}
