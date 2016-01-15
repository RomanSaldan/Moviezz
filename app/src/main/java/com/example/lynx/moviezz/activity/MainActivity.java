package com.example.lynx.moviezz.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.lynx.moviezz.R;
import com.example.lynx.moviezz.fragment.MovieDetailFragment;
import com.example.lynx.moviezz.fragment.PersonDetailFragment;
import com.example.lynx.moviezz.global.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUI();
    }

    //region view binds
    @Nullable
    @Bind(R.id.toolbar_AM)
    protected Toolbar toolbar_AM;

    @Nullable
    @Bind(R.id.navView_AM)
    protected NavigationView navView_AM;

    @Nullable
    @Bind(R.id.rootLayout_AM)
    protected DrawerLayout rootLayout_AM;

    @Nullable
    @Bind(R.id.fragmentContainer_AM)
    protected FrameLayout fragmentContainer_AM;
    //endregion

    private void initUI() {
        setSupportActionBar(toolbar_AM);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,  rootLayout_AM, toolbar_AM,
                R.string.drawer_open, R.string.drawer_close);
        rootLayout_AM.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        assert navView_AM != null;
        navView_AM.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuItemAbout:
                        getSupportActionBar().hide();
                        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
                        Bundle movieBundle = new Bundle();
                        movieBundle.putInt(Constants.EXTRA_MOVIE_ID, 15);
                        movieDetailFragment.setArguments(movieBundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, movieDetailFragment).addToBackStack("movieTag").commit();
                        break;
                    case R.id.menuItemCalendar:
                        getSupportActionBar().hide();
                        PersonDetailFragment personDetailFragment = new PersonDetailFragment();
                        Bundle personBundle = new Bundle();
                        personBundle.putInt(Constants.EXTRA_PERSON_ID, 6193);
                        personDetailFragment.setArguments(personBundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_AM, personDetailFragment).addToBackStack("personTag").commit();
                        break;
                }
                //TODO handle menu clicks;
                item.setChecked(true);
                rootLayout_AM.closeDrawers();
                toolbar_AM.setTitle(item.getTitle());
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0) getSupportFragmentManager().popBackStack();
        else super.onBackPressed();
    }
}
