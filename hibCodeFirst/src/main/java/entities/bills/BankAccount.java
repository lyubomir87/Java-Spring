package entities.bills;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BankAccount extends BillingDetail {
    private String bankName;
    private String swiftCode;

    public BankAccount() {
    }

    public BankAccount(User user, int number, String bankName, String swiftCode) {
        super(user, number);
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    @Column(
            name = "bank_name"
    )
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(
            name = "swift_code"
    )
    public String getSwiftCode() {
        return this.swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
