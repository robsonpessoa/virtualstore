package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "vs_product_value", schema = "vstore", catalog = "vstore_db")
@IdClass(ProductValuePK.class)
public class ProductValue {
    private int id;
    private int productId;
    private Double value;
    private Timestamp creationDate;
    private Product product;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "value", nullable = false)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
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

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
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
