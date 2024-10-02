package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EntityRepository<T> {
    private final Map<String, T> entities = new HashMap<>();

    public void save(String key, T entity) {
        if (entities.containsKey(key)) {
            throw new IllegalArgumentException("Entity with key " + key + " already exists");
        }
        entities.put(key, entity);
    }

    public Optional<T> findById(String key) {
        return Optional.ofNullable(entities.get(key));
    }

    public void delete(String key) {
        if (!entities.containsKey(key)) {
            throw new IllegalArgumentException("Entity with key " + key + " does not exist");
        }
        entities.remove(key);
    }
}

