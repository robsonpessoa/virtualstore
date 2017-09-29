package br.org.rpessoa.vstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_card", schema = "vstore", catalog = "vstore_db")
public class UserCard {
    @EmbeddedId
    private UserCardId id;
    @Basic
    @Column(name = "number", nullable = false, length = 12)
    private String number;
    @Basic
    @Column(name = "security_number", nullable = false)
    private int securityNumber;
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public UserCardId getId() {
        return id;
    }

    public void setId(UserCardId id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(int securityNumber) {
        this.securityNumber = securityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCard userCard = (UserCard) o;

        if (id.equals(userCard.id)) return false;
        if (securityNumber != userCard.securityNumber) return false;
        if (number != null ? !number.equals(userCard.number) : userCard.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + securityNumber;
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }
}
