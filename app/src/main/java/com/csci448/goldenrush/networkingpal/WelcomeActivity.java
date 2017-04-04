package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private Button mNewEventButton;
    private Button mNewApplicationButton;
    private Button mNewContactButton;
    private Button mDiggernetButton;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;


    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, WelcomeActivity.class);
        return intent ;
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

        mNewEventButton = (Button) findViewById(R.id.new_event_button);
        mNewEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * goes to events
                 */
                Intent i = NewEventActivity.newIntent(WelcomeActivity.this, null);
                startActivity(i);
            }
        });

        mNewApplicationButton = (Button) findViewById(R.id.new_app_button);
        mNewApplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * goes to application list view
                 */
                Intent intent = NewApplicationActivity.newIntent(WelcomeActivity.this, null);
                startActivity(intent);
            }
        });

        mNewContactButton = (Button) findViewById(R.id.new_contact_button);
        mNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = WelcomeActivity.newIntent(WelcomeActivity.this);
                Intent intent = NewContactActivity.newIntent(WelcomeActivity.this, null, i);
                Contact mContact = new Contact();
                ContactLab.get(getApplicationContext()).addContact(mContact);
                startActivity(intent);
            }
        });

        mDiggernetButton = (Button) findViewById(R.id.new_company_button);
        mDiggernetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * goes to diggernet page
                 */
                Intent ii = WelcomeActivity.newIntent(WelcomeActivity.this);
                Intent i = NewCompanyActivity.newIntent(WelcomeActivity.this, null, ii);
                startActivity(i);
            }
        });

        /**
         * Loads the recent action recyclerview
         */
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_welcome_list_host);

        if (fragment == null){
            fragment = new RecentActionFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_welcome_list_host, fragment)
                    .commit();
        }
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
