package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "payment_type")
public class PaymentType extends Model {

    @Column(name = "name", unique = true, notNull = true) // TODO [GG] on delete
    private String name;

    public PaymentType() {}

    public PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> payments() {
        return getMany(Payment.class, "type");
    }
}
