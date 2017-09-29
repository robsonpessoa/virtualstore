package br.org.rpessoa.vstore.dao;

import br.org.rpessoa.vstore.model.User;

import javax.persistence.Query;
import java.util.Collection;

public class UserDAO extends GenericDAO<User> {

    public Collection<User> listAll() {
        Query query = entityManager.createQuery("SELECT u FROM User u ");
        return query.getResultList();
    }

}
