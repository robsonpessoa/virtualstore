package br.org.rpessoa.vstore.dao;

import br.org.rpessoa.vstore.model.UserAccount;

import javax.persistence.Query;

public class UserAccountDAO extends GenericDAO<UserAccount> {
    public UserAccount findByUsernameAndPassword(String username, String password) {
        Query query = entityManager.createQuery("SELECT u FROM UserAccount u " +
                "WHERE u.username = :username " +
                "AND u.password = :password", UserAccount.class);
        return (UserAccount) query.getSingleResult();
    }
}
