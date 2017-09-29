package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class UserPurchaseProductId implements Serializable {

    private UserPurchaseId purchaseId;

    private ProductValueId productValueId;

    public UserPurchaseId getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(UserPurchaseId purchaseId) {
        this.purchaseId = purchaseId;
    }

    public ProductValueId getProductValueId() {
        return productValueId;
    }

    public void setProductValueId(ProductValueId productId) {
        this.productValueId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPurchaseProductId that = (UserPurchaseProductId) o;

        if (purchaseId != that.purchaseId) return false;
        if (productValueId != that.productValueId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = purchaseId.hashCode();
        result = 31 * result + productValueId.hashCode();
        return result;
    }
}
