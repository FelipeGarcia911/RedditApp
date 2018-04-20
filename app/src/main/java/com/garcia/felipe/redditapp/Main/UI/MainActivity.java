package com.garcia.felipe.redditapp.Main.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.garcia.felipe.redditapp.HomeList.UI.HomeListFragment;
import com.garcia.felipe.redditapp.Main.Presenter.MainPresenter;
import com.garcia.felipe.redditapp.Main.Presenter.MainPresenterImp;
import com.garcia.felipe.redditapp.R;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private MainPresenter presenter;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        presenter = new MainPresenterImp(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if (id == R.id.nav_camera) {
            presenter.onMainItemClick();
        } else{
            presenter.onMainItemClick();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    public void navToDetailsFragment() {
        currentFragment = new HomeListFragment();
        String fragmentName = String.valueOf("Details");
        if (!isFragmentVisible(fragmentName)) {
            executeFragmentTransaction(currentFragment, fragmentName);
        }
    }

    @Override
    public void navToHomeListViewFragment() {
        currentFragment = new HomeListFragment();
        String fragmentName = String.valueOf("Main List");
        if (!isFragmentVisible(fragmentName)) {
            executeFragmentTransaction(currentFragment, fragmentName);
        }
    }

    private boolean isFragmentVisible(String fragmentName) {
        Fragment myFragment = fragmentManager.findFragmentByTag(fragmentName);
        return (myFragment != null && myFragment.isVisible());
    }
}
