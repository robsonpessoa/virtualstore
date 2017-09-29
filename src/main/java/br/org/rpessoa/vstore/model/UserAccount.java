package br.org.rpessoa.vstore.model;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_account", schema = "vstore", catalog = "vstore_db")
public class UserAccount {
    @Id
    @Column(name = "username", nullable = false)
    private String username;
    @Basic
    @Column(name = "password", nullable = false)
    private String password;
    @Basic
    @Column(name = "role", nullable = false)
    private int role;
    @OneToOne(mappedBy = "account")
    private User user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
