package database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import entity.Entity;
import exception.DatabaseException;

public class Database {
    private Map<Class<? extends Entity>, DatabaseTableI<? extends Entity>> tables = new HashMap<>();

    public <T extends Entity> void save(Class<T> clazz, DatabaseTableI<T> databaseTable) throws DatabaseException {
        tables.put(clazz, databaseTable);
    }

    public <T extends Entity> Optional<T> findById(Class<T> clazz, int id) throws DatabaseException {

    }

    List<T> findAll() throws DatabaseException;

    void update(int id, T entity) throws DatabaseException;

    void delete(int id) throws DatabaseException;

    public <T extends Entity> void delete(Class<T> clazz, int id) throws DatabaseException {
        if (!tables.containsKey(clazz)) {
            tables.put(clazz, new DatabaseTable<>());
        }
        tables.get(clazz).delete(id);
    }
}
