package br.org.rpessoa.vstore.model;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_payment", schema = "vstore", catalog = "vstore_db")
public class UserPayment {
    private int id;
    private int type;
    private Integer billNumber;
    private User user;
    private UserCard userCard;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "bill_number", nullable = true)
    public Integer getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(Integer billNumber) {
        this.billNumber = billNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPayment that = (UserPayment) o;

        if (id != that.id) return false;
        if (type != that.type) return false;
        if (billNumber != null ? !billNumber.equals(that.billNumber) : that.billNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type;
        result = 31 * result + (billNumber != null ? billNumber.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "card_id", referencedColumnName = "id"), @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    public UserCard getUserCard() {
        return userCard;
    }

    public void setUserCard(UserCard vsUserCard) {
        this.userCard = vsUserCard;
    }
}
