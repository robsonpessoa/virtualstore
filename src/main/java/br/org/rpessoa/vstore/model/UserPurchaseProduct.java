package br.org.rpessoa.vstore.model;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_purchase_product", schema = "vstore", catalog = "vstore_db")
public class UserPurchaseProduct {
    @EmbeddedId
    private UserPurchaseProductId purchaseId;

    @Basic
    @Column(name = "items", nullable = false)
    private int items;

    @MapsId("purchaseId")
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "purchase_id", referencedColumnName = "id", nullable = false), @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)})
    private UserPurchase userPurchase;

    @MapsId("productValueId")
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "product_value_id", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)})
    private ProductValue productValue;

    public UserPurchaseProductId getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(UserPurchaseProductId purchaseId) {
        this.purchaseId = purchaseId;
    }

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

        if (!purchaseId.equals(that.purchaseId)) return false;
        if (items != that.items) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = purchaseId.hashCode();
        result = 31 * result + items;
        return result;
    }

    public UserPurchase getUserPurchase() {
        return userPurchase;
    }

    public void setUserPurchase(UserPurchase vsUserPurchase) {
        this.userPurchase = vsUserPurchase;
    }

    public ProductValue getProductValue() {
        return productValue;
    }

    public void setProductValue(ProductValue vsProductValue) {
        this.productValue = vsProductValue;
    }
}
