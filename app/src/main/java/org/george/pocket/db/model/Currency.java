package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Currency")
public class Currency extends Model {

    @Column(name = "code", notNull = true)
    private String code;

    public List<Payment> payments() {
        return getMany(Payment.class, "currency");
    }
}
