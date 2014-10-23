package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "payment")
public class Payment extends Model {

    @Column(name = "create_date")
    private Date creation;

    @Column(name = "amount", notNull = true)
    private Integer amount;

    @Column(name = "currency", notNull = true)
    private Currency currency;

    @Column(name = "type", notNull = true)
    private PaymentType type;

    public Payment() {}

    public Payment(Integer amount, Currency currency, PaymentType type) {
        this.creation = new Date();
        this.amount = amount;
        this.type = type;
        this.currency = currency;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }
}
