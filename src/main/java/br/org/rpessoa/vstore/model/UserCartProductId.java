package br.org.rpessoa.vstore.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
public class UserCartProductId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "product_id", nullable = false)
    private int productId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

        UserCartProductId that = (UserCartProductId) o;

        if (userId != that.userId) return false;
        if (productId != that.productId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + productId;
        return result;
    }
}
