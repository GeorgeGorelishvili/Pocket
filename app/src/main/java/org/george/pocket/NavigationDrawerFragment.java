package org.george.pocket;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.george.pocket.adapter.NavDrawerListAdapter;
import org.george.pocket.model.NavItem;
import org.george.pocket.model.NavMenuItem;

import java.util.ArrayList;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

    // A pointer to the current callbacks instance (the Activity).
    private NavigationDrawerCallback navigationDrawerCallback;

    private NavMenuItem currentItem;
    private ArrayList<NavMenuItem> navMenuItems;

    // Helper component that ties the action bar to the navigation drawer.
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout drawerLayout;
    private ListView listView;
    private View fragmentContainerView;

    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    public NavigationDrawerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onCreate");
        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(C.STATE.PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null && savedInstanceState.getSerializable(C.STATE.STATE_SELECTED_POSITION) != null) {
            currentItem = (NavMenuItem)savedInstanceState.getSerializable(C.STATE.STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        } else {
            currentItem = navMenuItems.get(0);
        }

        // Select either the default item (0) or the last selected item.
        selectItem(currentItem);
    }

    private void initNavDrawerItems() {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "initNavDrawerItems");
        String[] navMenuTitles = getResources().getStringArray(R.array.nav_drawer_titles);
        TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navMenuItems = new ArrayList<>();
        for (NavItem item : NavItem.values()) {
            navMenuItems.add(new NavMenuItem(item, navMenuTitles[item.ordinal()], navMenuIcons.getResourceId(item.ordinal(), -1), item.ordinal()));
        }
        navMenuIcons.recycle();
        currentItem = navMenuItems.get(0);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onActivityCreated");
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onCreateView");
        listView = (ListView) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(navMenuItems.get(position));
            }
        });
        listView.setAdapter(new NavDrawerListAdapter(NavigationDrawerFragment.this.getActivity(), navMenuItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(navMenuItems.get(position));
            }
        });
        return listView;
    }

    public boolean isDrawerOpen() {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "isDrawerOpen");
        return drawerLayout != null && drawerLayout.isDrawerOpen(fragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "setUp");
        fragmentContainerView = getActivity().findViewById(fragmentId);
        this.drawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        this.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                NavigationDrawerFragment.this.drawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(C.STATE.PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            this.drawerLayout.openDrawer(fragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        this.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        this.drawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(NavMenuItem item) {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "selectItem");
        currentItem = item;
        if (listView != null) {
            listView.setItemChecked(currentItem.getPosition(), true);
        }
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(fragmentContainerView);
        }
        if (navigationDrawerCallback != null) {
            navigationDrawerCallback.onNavigationDrawerItemSelected(currentItem);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        initNavDrawerItems();
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onAttach");
        super.onAttach(activity);

        try {
            navigationDrawerCallback = (NavigationDrawerCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onDetach");
        navigationDrawerCallback = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onSaveInstanceState");
        outState.putSerializable(C.STATE.STATE_SELECTED_POSITION, currentItem);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onConfigurationChanged");
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onCreateOptionsMenu");
        if (drawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
//            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "onOptionsItemSelected");
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_example) {
            Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "showGlobalContextActionBar");
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    public NavMenuItem getCurrentItem() {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "getCurrentItem");
        return currentItem;
    }

    public void setCurrentItem(NavMenuItem currentItem) {
        Logger.log(C.TAG.NAVIGATION_DRAWER_FRAGMENT, "setCurrentItem", currentItem != null ? currentItem.getTitle() : null);
        this.currentItem = currentItem;
    }

    public static interface NavigationDrawerCallback {
        void onNavigationDrawerItemSelected(NavMenuItem navMenuItem);
    }
}
