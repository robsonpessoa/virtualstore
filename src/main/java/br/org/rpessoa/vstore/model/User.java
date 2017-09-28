package br.org.rpessoa.vstore.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vs_user", schema = "vstore", catalog = "vstore_db")
public class User {
    private int id;
    private String name;
    private String surname;
    private String cpf;
    private String cnpj;
    private int role;
    private String email;
    private Collection<UserAddress> addresses;
    private Collection<UserCard> cards;
    private UserCart cart;
    private Collection<UserPhone> phones;
    private Collection<UserPurchase> purchases;

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
    @Column(name = "name", nullable = false, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "cpf", nullable = true, length = 11)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Basic
    @Column(name = "cnpj", nullable = true, length = 14)
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Basic
    @Column(name = "role", nullable = false)
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 150)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (role != user.role) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (cpf != null ? !cpf.equals(user.cpf) : user.cpf != null) return false;
        if (cnpj != null ? !cnpj.equals(user.cnpj) : user.cnpj != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (cnpj != null ? cnpj.hashCode() : 0);
        result = 31 * result + role;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user")
    public Collection<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<UserAddress> vsUserAddressesById) {
        this.addresses = vsUserAddressesById;
    }

    @OneToMany(mappedBy = "user")
    public Collection<UserCard> getCards() {
        return cards;
    }

    public void setCards(Collection<UserCard> vsUserCardsById) {
        this.cards = vsUserCardsById;
    }

    @OneToOne(mappedBy = "user")
    public UserCart getCart() {
        return cart;
    }

    public void setCart(UserCart vsUserCartById) {
        this.cart = vsUserCartById;
    }

    @OneToMany(mappedBy = "user")
    public Collection<UserPhone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<UserPhone> vsUserPhonesById) {
        this.phones = vsUserPhonesById;
    }

    @OneToMany(mappedBy = "user")
    public Collection<UserPurchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<UserPurchase> vsUserPurchasesById) {
        this.purchases = vsUserPurchasesById;
    }
}
