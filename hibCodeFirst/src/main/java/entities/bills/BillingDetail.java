package entities.bills;

import javax.persistence.*;

@Entity
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
public abstract class BillingDetail {
    private int id;
    private User user;
    private int number;

    public BillingDetail() {
    }

    public BillingDetail(User user, int number) {
        this.user = user;
        this.number = number;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(
            cascade = {CascadeType.ALL}
    )
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
