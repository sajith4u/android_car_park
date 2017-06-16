package park.innova.dev.carpark;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import park.innova.dev.carpark.fragments.HistoryFragment;
import park.innova.dev.carpark.fragments.HomeFragment;
import park.innova.dev.carpark.util.ContextHelper;

public class MainActivity extends AppCompatActivity{

    private static String TAG = "MainActivity";

    private DrawerLayout drawer;

    private static Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        updateInitialFragment();

        currentActivity = MainActivity.this;

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageView navigationBackgroundImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.navigation_header_image);
        ImageView appIconView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.navigation_app_icon);
        ContextHelper.loadImage("drawable://" + R.drawable.abstact2, navigationBackgroundImage);
        ContextHelper.loadImage("drawable://" + R.mipmap.ic_launcher, appIconView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_action) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_slideshow:
                fragmentClass = HistoryFragment.class;
                break;
            default:
                fragmentClass = HomeFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            Log.e(TAG, "Failed to get Fragment");
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).addToBackStack("").commit();
            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
        }
        drawer.closeDrawers();

    }

    private void updateInitialFragment() {
        Fragment fragment = null;
        Class fragmentClass = HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            Log.e(TAG, "Failed to get Fragment");
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();
    }
}
