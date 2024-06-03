package dao;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import database.Database;
import entity.Product;
import exception.DAOException;
import exception.DatabaseException;

public class ProductDao implements DAO<Product> {
    private Database database = Database.getInstance();

    @Override
    public void save(Product entity) throws DAOException, DatabaseException {
        database.save(Product.class, entity);
    }

    @Override
    public Optional<Product> findById(int id) throws DAOException {

    }

    @Override
    public List<Product> findAll() throws DAOException {

    }

    @Override
    public List<Product> findAll(Predicate<Product> filter) throws DAOException {

    }

    @Override
    public List<Product> findAll(Comparator<Product> comparator) throws DAOException {

    }

    @Override
    public void update(int id, Product entity) throws DAOException {

    }

    @Override
    public void delete(int id) throws DAOException {

    }
}
