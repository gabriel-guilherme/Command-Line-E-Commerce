package dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import database.Database;
import entity.Teacher;
import exception.DAOException;
import exception.DatabaseException;

public class TeacherDao implements DAO<Teacher> {
    private Database database = Database.getInstance();

    @Override
    public void save(Teacher entity) throws DAOException {
        try {
            database.save(Teacher.class, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to save teacher", e);
        }
    }

    @Override
    public Optional<Teacher> findById(int id) throws DAOException {
        try {
            return database.findById(Teacher.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find a teacher by id", e);
        }
    }

    @Override
    public List<Teacher> findAll() throws DAOException {
        try {
            return database.findAll(Teacher.class);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all teachers", e);
        }
    }

    @Override
    public List<Teacher> findAll(Predicate<Teacher> filter) throws DAOException {
        try {
            return database.findAll(Teacher.class).stream().filter(filter).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all filtered teachers", e);
        }
    }

    @Override
    public List<Teacher> findAll(Comparator<Teacher> comparator) throws DAOException {
        try {
            return database.findAll(Teacher.class).stream().sorted(comparator).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all comparated teachers", e);
        }
    }

    @Override
    public void update(int id, Teacher entity) throws DAOException {
        try {
            database.update(Teacher.class, id, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to update teacher", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            database.delete(Teacher.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to delete teacher", e);
        }
    }
}
