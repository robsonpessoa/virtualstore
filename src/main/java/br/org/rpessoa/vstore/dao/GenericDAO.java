package br.org.rpessoa.vstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;

public class GenericDAO<T> {
    @PersistenceContext
    protected static EntityManager entityManager = ConnectionFactory.getEntityManager();
    protected static PersistenceUnitUtil util = ConnectionFactory.getPersistenceUnitUtil();

    public static void refresh(Object object) {
        entityManager.refresh(object);
    }

    public void saveOrUpdate(T obj) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (util.getIdentifier(obj) == null)
                entityManager.persist(obj);
            else
                entityManager.merge(obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Not saved.", e);
        }
    }

    public T findById(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }

    public void remove(Class<T> clazz, Object id) {
        T t = findById(clazz, id);
        remove(t);
    }

    public void remove(T object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("not removed.", e);
        }
    }
}
