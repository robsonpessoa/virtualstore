package br.org.rpessoa.vstore.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAccountDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserAccountDetails(User user, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return user.getId();
    }
}
