package br.org.rpessoa.vstore.dao;

import br.org.rpessoa.vstore.model.Product;
import br.org.rpessoa.vstore.model.ProductValue;

import javax.persistence.Query;
import java.util.Collection;

public class ProductDAO extends GenericDAO<Product> {

    public Collection<Product> listAll() {
        Query query = entityManager.createQuery("SELECT p FROM Product p ");
        return query.getResultList();
    }

    public ProductValue getCurrentValue(Integer id) {
        Query query = entityManager.createQuery("SELECT v " +
                "FROM Product p, ProductValue v " +
                "WHERE p.valueId = v.id " +
                "AND p.id = :id", ProductValue.class);
        return (ProductValue) query.setParameter("id", id).getSingleResult();
    }
}
