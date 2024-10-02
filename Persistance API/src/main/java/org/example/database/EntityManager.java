package org.example.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EntityManager {
    private static EntityManagerFactory entityManagerFactory;
    private final Class cClass;

    public EntityManager(Class cClass) {
        entityManagerFactory = Persistence.createEntityManagerFactory(cClass.getSimpleName());
        this.cClass = cClass;
    }

    public void save(Object entity) {
        javax.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            System.out.println("Entity: " + entity + " saved");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Object findById(String id) {
        javax.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Object object = entityManager.find(cClass, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return object;
    }

    public List<Object> getAll() {
        javax.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String jpql = "SELECT m FROM " + cClass.getSimpleName() +  " m"; //
        Query query = entityManager.createQuery(jpql);

        List<Object> objects = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return objects;
    }

    public void deleteById(String id) {
        javax.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Object object = entityManager.find(cClass, id);
        if (object != null) {
            entityManager.remove(object);
        }
        else {
            System.out.println("Nie ma takiego rekordu w bazie");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Object> getByQuery(String query) {
        javax.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query q = entityManager.createQuery(query);

        List<Object> objects = q.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return objects;
    }
}
