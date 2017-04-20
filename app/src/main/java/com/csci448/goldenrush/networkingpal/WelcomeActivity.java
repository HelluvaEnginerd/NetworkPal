package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class WelcomeActivity extends AppCompatActivity{


    /**
     * TODO plus button w/list of new things
     * TODO put everything else in the menu_main (lists and such)
     */

    private static String TAG = WelcomeActivity.class.getSimpleName();
    private int mPosition;
    private static final String  EXTRA_POSITION = "position";

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private FloatingActionButton mFABAddThing;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;


    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, WelcomeActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_welcome_drawer);

        //******* Drawer things *******
        mDrawerList = (ListView)findViewById(R.id.welcome_navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.welcome_drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //******* Drawer things *******

        mPosition = getIntent().getIntExtra(EXTRA_POSITION, 0);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Apps"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.addTab(tabLayout.newTab().setText("People"));
        tabLayout.addTab(tabLayout.newTab().setText("Companies"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(mPosition);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                mPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mFABAddThing = (FloatingActionButton) findViewById(R.id.fab_add_contacts);
        mFABAddThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB add thing");
                if (tabLayout.getSelectedTabPosition() == 0){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 0);
                    Log.d(TAG, "start newApplicationActivity");
                    Intent intent = NewApplicationActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (tabLayout.getSelectedTabPosition() == 1){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 1);
                    Log.d(TAG, "start newEventActivity");
                    Intent intent = NewEventActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (tabLayout.getSelectedTabPosition() == 2) {
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 2);
                    Log.d(TAG, "start newContactActivity");
                    Intent intent = NewContactActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                } else if (tabLayout.getSelectedTabPosition() == 3){
                    Intent i = WelcomeActivity.newIntent(WelcomeActivity.this, 3);
                    Log.d(TAG, "Start newCompanyActivity");
                    Intent intent = NewCompanyActivity.newIntent(WelcomeActivity.this, null, i);
                    startActivity(intent);
                }
            }
        });
    }

    private void addDrawerItems() {
        String[] activityArray = { getResources().getString(R.string.applications), getResources().getString(R.string.calendar), getResources().getString(R.string.contacts), getResources().getString(R.string.diggernet) };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: Toast.makeText(getApplicationContext(), "Applications", Toast.LENGTH_SHORT).show();
                        Intent intent = ApplicationSearchActivity.newIntent(getApplicationContext());
                        startActivity(intent);
                        break;
                    case 1: Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT).show();
                        Intent intent1 = CalendarActivity.newIntent(getApplicationContext());
                        startActivity(intent1);
                        break;
                    case 2: Toast.makeText(getApplicationContext(), "Contacts", Toast.LENGTH_SHORT).show();
                        Intent intent2 = ContactsActivity.newIntent(getApplicationContext(), 0);
                        startActivity(intent2);
                        break;
                    case 3: Toast.makeText(getApplicationContext(), "Diggernet", Toast.LENGTH_SHORT).show();
                        Intent intent3 = DiggernetActivity.newIntent(getApplicationContext());
                        startActivity(intent3);
                        break;
                    default: Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Currently no settings :)", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
