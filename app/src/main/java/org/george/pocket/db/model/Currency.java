package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Currency")
public class Currency extends Model {

    @Column(name = "code", notNull = true)
    private String code;

    @Column(name = "default_currency")
    private boolean appCurrency;

    public Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isAppCurrency() {
        return appCurrency;
    }

    public void setAppCurrency(boolean appCurrency) {
        this.appCurrency = appCurrency;
    }

    public List<Payment> payments() {
        return getMany(Payment.class, "currency");
    }
}
