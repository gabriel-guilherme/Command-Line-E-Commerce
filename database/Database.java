package database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import entity.Entity;
import exception.DatabaseException;

public class Database {
    private static Database instance;
    private Map<Class<? extends Entity>, DatabaseTableI<? extends Entity>> tables = new HashMap<>();

    public Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private <T extends Entity> DatabaseTableI<T> getTable(Class<T> clazz) {
        return (DatabaseTableI<T>) tables.get(clazz);
    }

    public <T extends Entity> void save(Class<T> clazz, T entity) throws DatabaseException {
        if (!tables.containsKey(clazz)) {
            tables.put(clazz, new DatabaseTable<>());
        }
        getTable(clazz).save(entity);
    }

    public <T extends Entity> Optional<T> findById(Class<T> clazz, int id) throws DatabaseException {
        if (!tables.containsKey(clazz)) {
            tables.put(clazz, new DatabaseTable<>());
        }
        return getTable(clazz).findById(id);
    }

    public <T extends Entity> List<T> findAll(Class<T> clazz) throws DatabaseException {
        if (!tables.containsKey(clazz)) {
            tables.put(clazz, new DatabaseTable<>());
        }
        return getTable(clazz).findAll();
    }

    public <T extends Entity> void update(Class<T> clazz, int id, T entity) throws DatabaseException {
        if (!tables.containsKey(clazz)) {
            tables.put(clazz, new DatabaseTable<>());
        }
        getTable(clazz).update(id, entity);
    }

    public <T extends Entity> void delete(Class<T> clazz, int id) throws DatabaseException {
        if (!tables.containsKey(clazz)) {
            tables.put(clazz, new DatabaseTable<>());
        }
        tables.get(clazz).delete(id);
    }
}