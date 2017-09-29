package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "vs_product_value", schema = "vstore", catalog = "vstore_db")
public class ProductValue {
    @EmbeddedId
    private ProductValueId id;
    @Basic
    @Column(name = "value", nullable = false)
    private Double value;
    @Basic
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    public ProductValueId getId() {
        return id;
    }

    public void setId(ProductValueId id) {
        this.id = id;
    }


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductValue that = (ProductValue) o;

        if (!id.equals(that.id)) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product vsProductByProductId) {
        this.product = vsProductByProductId;
    }
}
