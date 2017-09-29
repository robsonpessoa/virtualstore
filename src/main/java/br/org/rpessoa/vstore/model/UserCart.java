package br.org.rpessoa.vstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.persistence.annotations.PrivateOwned;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vs_user_cart", schema = "vstore", catalog = "vstore_db")
public class UserCart {
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;

    @MapsId("userId")
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "userCart", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @PrivateOwned
    private Collection<UserCartProduct> products;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCart userCart = (UserCart) o;

        if (userId != userCart.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }

    public Collection<UserCartProduct> getProducts() {
        return products;
    }

    public void setProducts(Collection<UserCartProduct> vsUserCartProductsById) {
        this.products = vsUserCartProductsById;
    }
}
