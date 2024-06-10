package dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import database.Database;
import entity.Admin;
import exception.DAOException;
import exception.DatabaseException;

public class AdminDao implements DAO<Admin> {
    private Database database = Database.getInstance();

    @Override
    public void save(Admin entity) throws DAOException {
        try {
            database.save(Admin.class, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to save admin", e);
        }
    }

    @Override
    public Optional<Admin> findById(int id) throws DAOException {
        try {
            return database.findById(Admin.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find a admin by id", e);
        }
    }

    @Override
    public List<Admin> findAll() throws DAOException {
        try {
            return database.findAll(Admin.class);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all admins", e);
        }
    }

    @Override
    public List<Admin> findAll(Predicate<Admin> filter) throws DAOException {
        try {
            return database.findAll(Admin.class).stream().filter(filter).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all filtered admins", e);
        }
    }

    @Override
    public List<Admin> findAll(Comparator<Admin> comparator) throws DAOException {
        try {
            return database.findAll(Admin.class).stream().sorted(comparator).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all comparated admins", e);
        }
    }

    @Override
    public void update(int id, Admin entity) throws DAOException {
        try {
            database.update(Admin.class, id, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to update admin", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            database.delete(Admin.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to delete admin", e);
        }
    }
}
