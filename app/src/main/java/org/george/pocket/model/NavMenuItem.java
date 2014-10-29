package org.george.pocket.model;

import java.io.Serializable;

public class NavMenuItem implements Serializable {

    private NavItem navItem;
    private String title;
    private int icon;

    public NavMenuItem(NavItem navItem, String title, int icon) {
        this.navItem = navItem;
        this.title = title;
        this.icon = icon;
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

    public NavItem getNavItem() {
        return navItem;
    }

    public void setNavItem(NavItem navItem) {
        this.navItem = navItem;
    }
}
