package org.george.pocket;

public class C {

    public static class ARG {
        public static final String NAV_MENU_ITEM = "nav_menu_item";
    }

    public static class STATE {
        //Remember the position of the selected item.
        public static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

        // Per the design guidelines, you should show the drawer on launch until the user manually
        // expands it. This shared preference tracks this.
        public static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    }

    public static class TAG {
        public static final String MAIN_ACTIVITY = "MAIN_ACTIVITY";
        public static final String NAVIGATION_DRAWER_FRAGMENT = "NAVIGATION_DRAWER_FRAGMENT";
        public static final String CONSUMPTION_FRAGMENT = "CONSUMPTION_FRAGMENT";
        public static final String REPORT_FRAGMENT = "REPORT_FRAGMENT";
        public static final String CONFIGURATION_FRAGMENT = "CONFIGURATION_FRAGMENT";
        public static final String QUERY = "QUERY";

    }

    public static class DEF {
        public static final String DEFAULT_CURRENCY_CODE = "GEL";
        public static final String DEFAULT_TYPE_NAME = "მგზავრობა";
    }
}
