package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ProductValueId implements Serializable {
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id", nullable = false)
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        ProductValueId that = (ProductValueId) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        return result;
    }
}
