package br.org.rpessoa.vstore.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class UserPurchaseProductPK implements Serializable {
    private int purchaseId;
    private int userId;
    private int productId;

    @Column(name = "purchase_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "product_id", nullable = false)
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPurchaseProductPK that = (UserPurchaseProductPK) o;

        if (purchaseId != that.purchaseId) return false;
        if (userId != that.userId) return false;
        if (productId != that.productId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = purchaseId;
        result = 31 * result + userId;
        result = 31 * result + productId;
        return result;
    }
}