package br.org.rpessoa.vstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_phone", schema = "vstore", catalog = "vstore_db")
public class UserPhone {
    @EmbeddedId
    private UserPhoneId id;
    @Basic
    @Column(name = "number", nullable = false, length = 20)
    private String number;
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;

    public UserPhoneId getId() {
        return id;
    }

    public void setId(UserPhoneId id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPhone userPhone = (UserPhone) o;

        if (!id.equals(userPhone.id)) return false;
        if (number != null ? !number.equals(userPhone.number) : userPhone.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }
}
