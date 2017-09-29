package br.org.rpessoa.vstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "vs_user_purchase", schema = "vstore", catalog = "vstore_db")
public class UserPurchase {
    @EmbeddedId
    private UserPurchaseId id;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @Basic
    @Column(name = "date", nullable = false)
    private Timestamp date;
    @Basic
    @Column(name = "payment_id", nullable = false)
    private int paymentId;
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "userPurchase")
    private Collection<UserPurchaseProduct> userPurchaseProducts;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "payment_id", referencedColumnName = "id")
    private UserPayment payment;

    public UserPurchaseId getId() {
        return id;
    }

    public void setId(UserPurchaseId id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void addProduct(UserPurchaseProduct product) {
        product.setUserPurchase(this);
        this.userPurchaseProducts.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPurchase that = (UserPurchase) o;

        if (!id.equals(that.id)) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }

    public Collection<UserPurchaseProduct> getUserPurchaseProducts() {
        return userPurchaseProducts;
    }

    public void setUserPurchaseProducts(Collection<UserPurchaseProduct> vsUserPurchaseProducts) {
        this.userPurchaseProducts = vsUserPurchaseProducts;
    }

    public UserPayment getPayment() {
        return payment;
    }

    public void setPayment(UserPayment payment) {
        this.payment = payment;
    }
}
