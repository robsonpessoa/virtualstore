package br.org.rpessoa.vstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_address", schema = "vstore", catalog = "vstore_db")
public class UserAddress {
    @EmbeddedId
    private UserAddressId id;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "neighbourhood", nullable = true, length = 40)
    private String neighbourhood;
    @Basic
    @Column(name = "postal_code", nullable = true, length = 8)
    private String postalCode;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public UserAddressId getId() {
        return id;
    }

    public void setId(UserAddressId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAddress that = (UserAddress) o;

        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (neighbourhood != null ? !neighbourhood.equals(that.neighbourhood) : that.neighbourhood != null)
            return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (neighbourhood != null ? neighbourhood.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
}
