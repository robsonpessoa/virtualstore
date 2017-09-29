package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.net.URI;
import java.util.Collection;

@Entity
@Table(name = "vs_product", schema = "vstore", catalog = "vstore_db")
public class Product {
    private int id;
    private int items;
    private String name;
    private String description;
    private Collection<ProductValue> ProductValuesById;
    private URI picture;
    private int valueId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "items", nullable = false)
    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "image_url", nullable = true, length = -1)
    public URI getPicture() {
        return picture;
    }

    public void setPicture(URI picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "value_id", nullable = true, length = -1)
    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (items != product.items) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + items;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "product")
    public Collection<ProductValue> getProductValuesById() {
        return ProductValuesById;
    }

    public void setProductValuesById(Collection<ProductValue> productValuesById) {
        ProductValuesById = productValuesById;
    }
}
