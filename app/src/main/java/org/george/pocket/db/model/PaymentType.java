package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "payment_type")
public class PaymentType extends Model {

    @Column(name = "name", unique = true, notNull = true) // TODO [GG] on delete
    private String name;

    @Column(name = "default_type")
    private boolean appType;

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

    public boolean isAppType() {
        return appType;
    }

    public void setAppType(boolean appType) {
        this.appType = appType;
    }

    public List<Payment> payments() {
        return getMany(Payment.class, "type");
    }
}
