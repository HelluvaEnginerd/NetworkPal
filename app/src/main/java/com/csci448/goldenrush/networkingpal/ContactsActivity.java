package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Nick on 3/2/17.
 */

public class ContactsActivity extends AppCompatActivity{

    private static String TAG = "ContactsActivity";
    private static final String  EXTRA_POSITION = "position";
    private FloatingActionButton mFABaddContact;
    private int mPosition;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, ContactsActivity.class);
        intent.putExtra(EXTRA_POSITION, position);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_search);

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

        mFABaddContact = (FloatingActionButton) findViewById(R.id.fab_add_contacts);
        mFABaddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB add application");
                if(mPosition==0) {
                    Contact mContact = new Contact();
                    ContactLab.get(getApplicationContext()).addContact(mContact);
                } else{
                    //Company mCompany = new Company();
                    //CompanyLab.get(getApplicationContext()).addCompany(mCompany);
                }
                /**
                 * position 0 is people
                 * position 1 is companies
                 */
                Intent i = ContactsActivity.newIntent(ContactsActivity.this, 0);
                if (tabLayout.getSelectedTabPosition() == 0) {
                    Log.d(TAG, "start newContactActivity");
                    Contact newContact = new Contact();
                    ContactLab.get(getApplicationContext()).addContact(newContact);
                    Intent intent = NewContactActivity.newIntent(ContactsActivity.this, newContact.getUUID(), i);
                    startActivity(intent);
                }else {
                    Log.d(TAG, "Start newCompanyActivity");
                    Company newCompany = new Company();
                    CompanyLab.get(getApplicationContext()).addCompany(newCompany);
                    Intent intent = NewCompanyActivity.newIntent(ContactsActivity.this, newCompany.getID(), i);
                    startActivity(intent);
                }
            }
        });
    }



    private void addDrawerItems() {
        String[] activityArray = { getResources().getString(R.string.welcome), getResources().getString(R.string.applications), getResources().getString(R.string.calendar), getResources().getString(R.string.diggernet) };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = WelcomeActivity.newIntent(getApplicationContext());
                        startActivity(intent);
                        break;
                    case 1: Toast.makeText(getApplicationContext(), "Applications", Toast.LENGTH_SHORT).show();
                        Intent intent1 = ApplicationSearchActivity.newIntent(getApplicationContext());
                        startActivity(intent1);
                        break;
                    case 2: Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT).show();
                        Intent intent2 = CalendarActivity.newIntent(getApplicationContext());
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

