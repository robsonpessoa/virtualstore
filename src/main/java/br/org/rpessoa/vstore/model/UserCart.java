package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vs_user_cart", schema = "vstore", catalog = "vstore_db")
public class UserCart {
    private int userId;
    private User user;
    private Collection<UserCartProduct> vsUserCartProductsById;

    @Id
    @Column(name = "user_id", nullable = false)
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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }

    @OneToMany(mappedBy = "userCart")
    public Collection<UserCartProduct> getVsUserCartProductsById() {
        return vsUserCartProductsById;
    }

    public void setVsUserCartProductsById(Collection<UserCartProduct> vsUserCartProductsById) {
        this.vsUserCartProductsById = vsUserCartProductsById;
    }
}
