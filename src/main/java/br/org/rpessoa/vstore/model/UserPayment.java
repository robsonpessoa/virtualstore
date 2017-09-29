package br.org.rpessoa.vstore.model;

import javax.persistence.*;

@Entity
@Table(name = "vs_user_payment", schema = "vstore", catalog = "vstore_db")
public class UserPayment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "type", nullable = false)
    private int type;
    @Basic
    @Column(name = "bill_number", nullable = true)
    private Integer billNumber;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "card_id", referencedColumnName = "id"), @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private UserCard userCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User vsUserByUserId) {
        this.user = vsUserByUserId;
    }

    public UserCard getUserCard() {
        return userCard;
    }

    public void setUserCard(UserCard vsUserCard) {
        this.userCard = vsUserCard;
    }
}
