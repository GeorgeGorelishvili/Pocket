package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "payment")
public class Payment extends Model {

    @Column(name = "create_date", notNull = true)
    public Date creationDate;

    @Column(name = "amount", notNull = true)
    public Integer amount;

    @Column(name = "currency", notNull = true)
    public String currency;

    @Column(name = "type", notNull = true)
    public String type;

    public Payment() {
        this.creationDate = new Date();
    }

    public Payment(Integer amount, String type, String currency) {
        this.creationDate = new Date();
        this.amount = amount;
        this.type = type;
        this.currency = currency;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
