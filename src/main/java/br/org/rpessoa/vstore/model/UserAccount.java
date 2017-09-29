package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vs_user_account", schema = "vstore", catalog = "vstore_db")
public class UserAccount {
    private String username;
    private String password;
    private int role;

    private User user;

    public UserAccount() {}

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role", nullable = false)
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (role != that.role) return false;
        if (!username.equals(that.username)) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role;
        return result;
    }

    @OneToOne(mappedBy = "account")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
