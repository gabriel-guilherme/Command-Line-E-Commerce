package dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import database.Database;
import entity.Exam;
import exception.DAOException;
import exception.DatabaseException;

public class ExamDao implements DAO<Exam> {
    private Database database = Database.getInstance();

    @Override
    public void save(Exam entity) throws DAOException {
        try {
            database.save(Exam.class, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to save exam", e);
        }
    }

    @Override
    public Optional<Exam> findById(int id) throws DAOException {
        try {
            return database.findById(Exam.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find a exam by id", e);
        }
    }

    @Override
    public List<Exam> findAll() throws DAOException {
        try {
            return database.findAll(Exam.class);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all exams", e);
        }
    }

    @Override
    public List<Exam> findAll(Predicate<Exam> filter) throws DAOException {
        try {
            return database.findAll(Exam.class).stream().filter(filter).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all filtered exams", e);
        }
    }

    @Override
    public List<Exam> findAll(Comparator<Exam> comparator) throws DAOException {
        try {
            return database.findAll(Exam.class).stream().sorted(comparator).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new DAOException("Failed to find all comparated exams", e);
        }
    }

    @Override
    public void update(int id, Exam entity) throws DAOException {
        try {
            database.update(Exam.class, id, entity);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to update exam", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            database.delete(Exam.class, id);
        } catch (DatabaseException e) {
            throw new DAOException("Failed to delete exam", e);
        }
    }
}
