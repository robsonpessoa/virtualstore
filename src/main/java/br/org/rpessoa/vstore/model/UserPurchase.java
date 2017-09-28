package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "vs_user_purchase", schema = "vstore", catalog = "vstore_db")
@IdClass(UserPurchasePK.class)
public class UserPurchase {
    private int id;
    private int userId;
    private Timestamp date;
    private User user;
    private Collection<UserPurchaseProduct> userPurchaseProducts;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPurchase that = (UserPurchase) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }

    @OneToMany(mappedBy = "userPurchase")
    public Collection<UserPurchaseProduct> getUserPurchaseProducts() {
        return userPurchaseProducts;
    }

    public void setUserPurchaseProducts(Collection<UserPurchaseProduct> vsUserPurchaseProducts) {
        this.userPurchaseProducts = vsUserPurchaseProducts;
    }
}