package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "payment_type")
public class PaymentType extends Model {

    @Column(name = "name", unique = true, notNull = true)
    private String name;

    @Column(name = "active", notNull = true, length = 1)
    public boolean active;

    public PaymentType() {}

    public PaymentType(String name) {
        this.name = name;
    }

    public PaymentType(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
