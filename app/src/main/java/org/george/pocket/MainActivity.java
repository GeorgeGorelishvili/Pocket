package org.george.pocket;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.george.pocket.Consumption.ConsumptionFragment;
import org.george.pocket.adapter.NavDrawerListAdapter;
import org.george.pocket.model.NavItem;
import org.george.pocket.model.NavMenuItem;
import org.george.pocket.payment.PaymentFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle drawerToggle;

    private NavMenuItem currentItem;
    private ArrayList<NavMenuItem> navMenuItems;

    private boolean fromSavedInstanceState;
    private boolean userLearnedDrawer;

    // Used to store the last screen title. For use in {@link #restoreActionBar()}.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.log(C.TAG.MAIN_ACTIVITY, "onCreate");
        setContentView(R.layout.activity_main);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userLearnedDrawer = sp.getBoolean(C.STATE.PREF_USER_LEARNED_DRAWER, false);

        initNavDrawerItems();
        if (savedInstanceState != null && savedInstanceState.getSerializable(C.STATE.STATE_SELECTED_POSITION) != null) {
            currentItem = (NavMenuItem)savedInstanceState.getSerializable(C.STATE.STATE_SELECTED_POSITION);
            fromSavedInstanceState = true;
        } else {
            currentItem = navMenuItems.get(1);

        }
        initDrawerLayout();
        selectItem(currentItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.log(C.TAG.MAIN_ACTIVITY, "onOptionsItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_about) {
            Toast.makeText(this, "About action.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Logger.log(C.TAG.MAIN_ACTIVITY, "onCreateOptionsMenu");
        if (drawerLayout != null && !drawerLayout.isDrawerOpen(drawerListView)) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.log(C.TAG.MAIN_ACTIVITY, "onSaveInstanceState");

        outState.putSerializable(C.STATE.STATE_SELECTED_POSITION, currentItem);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.log(C.TAG.MAIN_ACTIVITY, "onConfigurationChanged");

        // Forward the new configuration the drawer toggle component.
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void restoreActionBar() {
        Logger.log(C.TAG.MAIN_ACTIVITY, "restoreActionBar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void initDrawerLayout() {
        Logger.log(C.TAG.MAIN_ACTIVITY, "initDrawerLayout");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        drawerListView = (ListView)findViewById(R.id.list_sliderMenu);
        drawerListView.setAdapter(new NavDrawerListAdapter(MainActivity.this, navMenuItems));
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(navMenuItems.get(position));
            }
        });

        drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this, drawerLayout, R.drawable.ic_drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(currentItem.getTitle());
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
                if (!userLearnedDrawer) {
                    userLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    sp.edit().putBoolean(C.STATE.PREF_USER_LEARNED_DRAWER, true).apply();
                }
                supportInvalidateOptionsMenu();
            }
        };

        if (!userLearnedDrawer && !fromSavedInstanceState) {
            drawerLayout.openDrawer(drawerListView);
        }

        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void selectItem(NavMenuItem item) {
        Logger.log(C.TAG.MAIN_ACTIVITY, "setSelection: " + item.getNavItem().name());
        // update the main content by replacing fragments
        Fragment fragment = EmptyFragment.newInstance(item);
        switch (item.getNavItem()) {
            case HOME:      addReplaceFragment(new ConsumptionFragment(), item, true);     break;
            case REPORT:    addReplaceFragment(new PaymentFragment(), item, true); break;
            case CONFIG:    addReplaceFragment(new ConsumptionFragment(), item, true); break;
            case NEXT:
            default: addReplaceFragment(new EmptyFragment(), item, true); break;
        }

        currentItem = item;
        if (drawerListView != null) {
            drawerListView.setItemChecked(currentItem.getNavItem().ordinal(), true);
            drawerListView.setSelection(currentItem.getNavItem().ordinal());
        }
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(drawerListView);
        }
        getSupportActionBar().setTitle(item.getTitle());
    }

    private void addReplaceFragment(Fragment fragment, NavMenuItem item, boolean replace) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (replace) {
            transaction.replace(R.id.container, fragment);
        } else {
            transaction.add(R.id.container, fragment);
        }
        transaction.commit();
    }

    private void initNavDrawerItems() {
        Logger.log(C.TAG.MAIN_ACTIVITY, "initNavDrawerItems");
        String[] navMenuTitles = getResources().getStringArray(R.array.nav_drawer_titles);
        TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navMenuItems = new ArrayList<>();
        for (NavItem item : NavItem.values()) {
            NavMenuItem menuItem = new NavMenuItem(item, navMenuTitles[item.ordinal()], navMenuIcons.getResourceId(item.ordinal(), -1));
            navMenuItems.add(menuItem);
        }
        navMenuIcons.recycle();
    }
}
