package br.org.rpessoa.vstore.model;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_purchase_product", schema = "vstore", catalog = "vstore_db")
@IdClass(UserPurchaseProductPK.class)
public class UserPurchaseProduct {
    private int purchaseId;
    private int userId;
    private int productId;
    private int productValueId;
    private int items;
    private UserPurchase userPurchase;
    private Product product;
    private ProductValue productValue;

    @Id
    @Column(name = "purchase_id", nullable = false)
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

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
    @Column(name = "product_value_id", nullable = false)
    public int getProductValueId() {
        return productValueId;
    }

    public void setProductValueId(int productValueId) {
        this.productValueId = productValueId;
    }

    @Basic
    @Column(name = "items", nullable = false)
    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPurchaseProduct that = (UserPurchaseProduct) o;

        if (purchaseId != that.purchaseId) return false;
        if (userId != that.userId) return false;
        if (productId != that.productId) return false;
        if (productValueId != that.productValueId) return false;
        if (items != that.items) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = purchaseId;
        result = 31 * result + userId;
        result = 31 * result + productId;
        result = 31 * result + productValueId;
        result = 31 * result + items;
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "purchase_id", referencedColumnName = "id", nullable = false), @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)})
    public UserPurchase getUserPurchase() {
        return userPurchase;
    }

    public void setUserPurchase(UserPurchase vsUserPurchase) {
        this.userId = userPurchase.getUserId();
        this.userPurchase = vsUserPurchase;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product vsProductByProductId) {
        this.productId = vsProductByProductId.getId();
        this.product = vsProductByProductId;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "product_value_id", referencedColumnName = "id", nullable = false), @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)})
    public ProductValue getProductValue() {
        return productValue;
    }

    public void setProductValue(ProductValue vsProductValue) {
        this.productValueId = vsProductValue.getId();
        this.productValue = vsProductValue;
    }
}
