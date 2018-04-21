package com.garcia.felipe.redditapp.Main.UI;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.garcia.felipe.redditapp.About.AboutFragment;
import com.garcia.felipe.redditapp.Details.UI.DetailFragment;
import com.garcia.felipe.redditapp.Helpers.LocalStorage.ListLocalStorageHelper;
import com.garcia.felipe.redditapp.Helpers.LocalStorage.SharedPreferencesHelper;
import com.garcia.felipe.redditapp.HomeList.UI.HomeListFragment;
import com.garcia.felipe.redditapp.Main.Presenter.MainPresenter;
import com.garcia.felipe.redditapp.Main.Presenter.MainPresenterImp;
import com.garcia.felipe.redditapp.Models.RedditPost;
import com.garcia.felipe.redditapp.R;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private MainPresenter presenter;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeSingletons();
        fragmentManager = getSupportFragmentManager();

        presenter = new MainPresenterImp(this);
        presenter.onCreate();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_list:
                presenter.onNavHomeList();
                break;
            case R.id.nav_about:
                presenter.onNavAbout();
                break;
            default:
                presenter.onNavHomeList();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void executeFragmentTransaction(final Fragment fragment, final String fragmentName) {
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.content_main, fragment, fragmentName)
                .addToBackStack(fragmentName)
                .commit();
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void navToDetailsFragment(RedditPost item) {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setRedditPost(item);
        currentFragment = detailFragment;
        String fragmentName = String.valueOf("Post Details");
        if (!isFragmentVisible(fragmentName)) {
            executeFragmentTransaction(currentFragment, fragmentName);
        }
    }

    @Override
    public void navToHomeListViewFragment() {
        currentFragment = new HomeListFragment();
        String fragmentName = String.valueOf("Reddit Post");
        if (!isFragmentVisible(fragmentName)) {
            executeFragmentTransaction(currentFragment, fragmentName);
        }
    }

    private boolean isFragmentVisible(String fragmentName) {
        Fragment myFragment = fragmentManager.findFragmentByTag(fragmentName);
        return (myFragment != null && myFragment.isVisible());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navToAboutFragment() {
        currentFragment = new AboutFragment();
        String fragmentName = String.valueOf("About");
        if (!isFragmentVisible(fragmentName)) {
            executeFragmentTransaction(currentFragment, fragmentName);
        }
    }

    private void initializeSingletons() {
        // Initialize Shared Preferences Helper
        SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.getInstance();
        sharedPreferencesHelper.initialize(this);

        ListLocalStorageHelper listLocalStorageHelper = ListLocalStorageHelper.getInstance();
        listLocalStorageHelper.initialize();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
