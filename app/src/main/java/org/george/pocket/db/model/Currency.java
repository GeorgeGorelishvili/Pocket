package org.george.pocket.db.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Currency")
public class Currency extends Model {

    @Column(name = "code", notNull = true, unique = true)
    public String code;

    @Column(name = "active", notNull = true, length = 1)
    public boolean active;

    public Currency() {}

    public Currency(String code) {
        this.code = code;
    }

    public Currency(String code, boolean active) {
        this.code = code;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
