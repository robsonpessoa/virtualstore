package br.org.rpessoa.vstore.model;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_cart_product", schema = "vstore", catalog = "vstore_db")
public class UserCartProduct {
    @EmbeddedId
    private UserCartProductId userId;
    @Basic
    @Column(name = "items", nullable = true)
    private Integer items;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserCart userCart;
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    public UserCartProductId getUserCartProductId() {
        return userId;
    }

    public void setUserCartProductId(UserCartProductId userId) {
        this.userId = userId;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCartProduct that = (UserCartProduct) o;

        if (!userId.equals(that.userId)) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    public UserCart getUserCart() {
        return userCart;
    }

    public void setUserCart(UserCart vsUserByUserId) {
        this.userCart = vsUserByUserId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product vsProductByProductId) {
        this.product = vsProductByProductId;
    }
}
