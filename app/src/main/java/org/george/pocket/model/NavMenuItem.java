package org.george.pocket.model;

import java.io.Serializable;

public class NavMenuItem implements Serializable {

    private NavItem navItem;
    private String title;
    private int icon;
    private int position;

    public NavMenuItem(NavItem navItem, String title, int icon, int position) {
        this.navItem = navItem;
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

    public NavItem getNavItem() {
        return navItem;
    }

    public void setNavItem(NavItem navItem) {
        this.navItem = navItem;
    }
}
