package org.example;

import java.util.Optional;

public class EntityController<T> {
    private final EntityRepository<T> repository;

    public EntityController(EntityRepository<T> repository) {
        this.repository = repository;
    }

    public String saveEntity(String key, T entity) {
        try {
            repository.save(key, entity);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }

    public String deleteEntity(String key) {
        try {
            repository.delete(key);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }

    public String findEntity(String key) {
        Optional<T> optionalEntity = repository.findById(key);
        if (optionalEntity.isPresent()) {
            T entity = optionalEntity.get();
            return entity.toString();
        } else {
            return "not found";
        }
    }
}

