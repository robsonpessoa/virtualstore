package br.org.rpessoa.vstore.model;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_cart_product", schema = "vstore", catalog = "vstore_db")
@IdClass(UserCartProductPK.class)
public class UserCartProduct {
    private int userId;
    private int productId;
    private Integer items;
    private UserCart userCart;
    private Product product;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "items", nullable = true)
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

        if (userId != that.userId) return false;
        if (productId != that.productId) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + productId;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserCart getUserCart() {
        return userCart;
    }

    public void setUserCart(UserCart vsUserByUserId) {
        this.userCart = vsUserByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product vsProductByProductId) {
        this.product = vsProductByProductId;
    }
}
