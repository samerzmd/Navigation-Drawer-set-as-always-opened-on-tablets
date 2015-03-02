package com.sam.navdrawerlockedplease;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;


public class MainActivity extends FragmentActivity {
    private boolean isDrawerLocked = false;
    private android.support.v4.widget.DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private String[] drawerItems;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);

        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.content_frame);
        if(((ViewGroup.MarginLayoutParams)frameLayout.getLayoutParams()).leftMargin == (int)getResources().getDimension(R.dimen.drawer_size)) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, drawerList);
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            isDrawerLocked = true;
        }

        // Set the adapter for the list view
        drawerItems = getResources().getStringArray(R.array.drawerOptions);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.actionSort,
                R.string.actionSort
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
            //    getActionBar().setTitle(title);
             //   ((FragmentInterface)fragment).showMenuActions();
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle("Select Option");
              //  ((FragmentInterface)fragment).hideMenuActions();
                invalidateOptionsMenu();
            }
        };

        if(!isDrawerLocked) {
            drawerLayout.setDrawerListener(drawerToggle);
        }

        // Set the drawer toggle as the DrawerListener
        DrawerItemClickListener drawerItemClickListener = new DrawerItemClickListener();
        drawerList.setOnItemClickListener(drawerItemClickListener);

        if(!isDrawerLocked) {
          //  getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        MenuItem item = null;
        if(item != null) {
            item.setVisible(!drawerOpen);
        }
        item = null;
        if(item != null) {
            item.setVisible(!drawerOpen);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * The drawer item click listener
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }

        /** Swaps fragments in the main content view */
        private void selectItem(int position) {
            Bundle bundle;
            switch(position) {
                default:
                    break;
            }
           /* FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();*/

            // Highlight the selected item, update the title, and close the drawer
            drawerList.setItemChecked(position, true);

            setTitle(MainActivity.this.drawerItems[position]);
            if(!isDrawerLocked) {
                drawerLayout.closeDrawer(drawerList);
            }
        }
    }
}