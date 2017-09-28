package br.org.rpessoa.vstore.dao;

import br.org.rpessoa.vstore.exception.DatabaseException;

import javax.persistence.*;

public class GenericDAO<T> {
    @PersistenceContext
    protected static EntityManager entityManager = ConnectionFactory.getEntityManager();
    protected static PersistenceUnitUtil util = ConnectionFactory.getPersistenceUnitUtil();

    public static void refresh(Object object) {
        entityManager.refresh(object);
    }

    public void saveOrUpdate(T obj) throws DatabaseException {
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
            throw new DatabaseException(e);
        }
    }

    public T findById(Class<T> clazz, Object id) {
        T result = entityManager.find(clazz, id);

        if (result == null)
            throw new NoResultException();

        return result;
    }

    public void remove(Class<T> clazz, Object id) throws DatabaseException {
        T t = findById(clazz, id);
        remove(t);
    }

    public void remove(T object) throws DatabaseException {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new DatabaseException(e);
        }
    }
}
