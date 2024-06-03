package dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import entity.Entity;
import exception.DAOException;

public interface DAO<T extends Entity> {
    void save(T entity) throws DAOException;

    Optional<T> findById(int id) throws DAOException;

    List<T> findAll() throws DAOException;

    List<T> findAll(Predicate<T> filter) throws DAOException;

    List<T> findAll(Comparator<T> comparator) throws DAOException;

    void update(int id, T entity) throws DAOException;

    void delete(int id) throws DAOException;
}
