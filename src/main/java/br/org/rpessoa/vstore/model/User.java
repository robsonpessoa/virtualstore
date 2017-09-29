package br.org.rpessoa.vstore.model;

import org.eclipse.persistence.annotations.PrivateOwned;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vs_user", schema = "vstore", catalog = "vstore_db")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;
    @Basic
    @Column(name = "cpf", nullable = true, length = 11)
    private String cpf;
    @Basic
    @Column(name = "cnpj", nullable = true, length = 14)
    private String cnpj;
    @Basic
    @Column(name = "role", nullable = false)
    private int role;
    @Basic
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @PrivateOwned
    private Collection<UserAddress> addresses;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @PrivateOwned
    private Collection<UserCard> cards;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserCart cart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @PrivateOwned
    private Collection<UserPhone> phones;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @PrivateOwned
    private Collection<UserPurchase> purchases;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private UserAccount account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

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

    public Collection<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<UserAddress> vsUserAddressesById) {
        this.addresses = vsUserAddressesById;
    }

    public Collection<UserCard> getCards() {
        return cards;
    }

    public void setCards(Collection<UserCard> vsUserCardsById) {
        this.cards = vsUserCardsById;
    }

    public UserCart getCart() {
        return cart;
    }

    public void setCart(UserCart vsUserCartById) {
        this.cart = vsUserCartById;
    }

    public Collection<UserPhone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<UserPhone> vsUserPhonesById) {
        this.phones = vsUserPhonesById;
    }

    public Collection<UserPurchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<UserPurchase> vsUserPurchasesById) {
        this.purchases = vsUserPurchasesById;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }
}
