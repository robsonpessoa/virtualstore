package br.org.rpessoa.vstore.dao;

import br.org.rpessoa.vstore.model.Product;

import javax.persistence.Query;
import java.util.Collection;

public class ProductDAO extends GenericDAO<Product> {

    public Collection<Product> listAll() {
        Query query = entityManager.createQuery("SELECT p FROM Product p ");
        return query.getResultList();
    }
}
