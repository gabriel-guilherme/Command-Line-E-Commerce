package dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import database.Database;
import entity.Student;
import exception.DAOException;
import exception.DatabaseException;

public class StudentDao implements DAO<Student> {
    private Database database = Database.getInstance();

    @Override
    public void save(Student entity) throws DAOException {
        try {
            database.save(Student.class, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to save student", e);
        }
    }

    @Override
    public Optional<Student> findById(int id) throws DAOException {
        try {
            return database.findById(Student.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find a student by id", e);
        }
    }

    @Override
    public List<Student> findAll() throws DAOException {
        try {
            return database.findAll(Student.class);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all students", e);
        }
    }

    @Override
    public List<Student> findAll(Predicate<Student> filter) throws DAOException {
        try {
            return database.findAll(Student.class).stream().filter(filter).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all filtered students", e);
        }
    }

    @Override
    public List<Student> findAll(Comparator<Student> comparator) throws DAOException {
        try {
            return database.findAll(Student.class).stream().sorted(comparator).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all comparated students", e);
        }
    }

    @Override
    public void update(int id, Student entity) throws DAOException {
        try {
            database.update(Student.class, id, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to update student", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            database.delete(Student.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to delete student", e);
        }
    }
}
