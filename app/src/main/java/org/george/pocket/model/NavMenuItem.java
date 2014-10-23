package org.george.pocket.model;

import java.io.Serializable;

public class NavMenuItem implements Serializable {

    private String title;
    private int icon;
    private int position;

    public NavMenuItem(String title, int icon, int position) {
        this.title = title;
        this.icon = icon;
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
