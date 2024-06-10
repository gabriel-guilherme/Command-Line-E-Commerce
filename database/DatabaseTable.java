package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import entity.Entity;
import exception.DatabaseException;

public class DatabaseTable<T extends Entity> implements DatabaseTableI<T> {
    private Map<Integer, T> table = new HashMap<>();

    @Override
    public void save(T entity) throws DatabaseException {
        if (entity == null) {
            throw new DatabaseException("Entity cannot be null");
        }
        entity.setId(table.size() + 1);
        table.put(entity.getId(), entity);
    }

    @Override
    public Optional<T> findById(int id) throws DatabaseException {
        if (!table.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(table.get(id));
    }

    @Override
    public List<T> findAll() throws DatabaseException {
        return new ArrayList<>(table.values());
    }

    @Override
    public void update(int id, T entity) throws DatabaseException {
        if (!table.containsKey(id)) {
            throw new DatabaseException("Entity with id " + id + " not found");
        }
        table.put(id, entity);
    }

    @Override
    public void delete(int id) throws DatabaseException {
        if (!table.containsKey(id)) {
            throw new DatabaseException("Entity with id " + id + " not found");
        }
        table.remove(id);
    }
}
