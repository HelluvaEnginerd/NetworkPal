package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sarah on 2/28/2017.
 * Displays calender to hold all event activities
 */

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "Calendar Activity";
    FloatingActionButton newEventButton;
    CalendarView calendarWidget;
    private TextView currentDateText;
    private String currentDateString;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    //Strings for save instance state
    private static final String DATE = "Date";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        //******* Drawer things *******
        mDrawerList = (ListView)findViewById(R.id.welcome_navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.welcome_drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //******* Drawer things *******

        setUpWidgets();
        if(savedInstanceState!=null)
        {
            calendarWidget.setDate(savedInstanceState.getLong(DATE));
        }
        //create the list of event fragments
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_events_container);

        if (fragment == null){
            fragment = new EventListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_events_container, fragment)
                    .commit();
        }
    }

    private void setUpWidgets(){

        Log.d(TAG, "setupWidgets()");
        newEventButton = (FloatingActionButton) findViewById(R.id.fab_add_event);
        calendarWidget = (CalendarView) findViewById(R.id.calendar_widget);

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
                EventLab.get(getApplicationContext()).addEvent(event);
                Intent i = NewEventActivity.newIntent(CalendarActivity.this, event.getId());
                startActivity(i);

            }
        });

        currentDateText= (TextView) findViewById(R.id.events_on_date);
        currentDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarWidget.getDate()));
        currentDateText.setText(currentDateString);

        //change the date showing
        calendarWidget.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //create new string for new selected date
                currentDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarWidget.getDate()));
                currentDateText.setText(currentDateString);

                //update the list of items based off of date
            }
        });

    }
    public static Intent newIntent(Context packageContext){
        Log.d(TAG, "newIntent()");
        Intent i = new Intent(packageContext, CalendarActivity.class);
        /**
         * TODO: Add any extras you may need when creating an event
         */

        return i ;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSaveInstanceStateCalled");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong(DATE, calendarWidget.getDate());

    }

    private void addDrawerItems() {
        String[] activityArray = { getResources().getString(R.string.welcome), getResources().getString(R.string.applications), getResources().getString(R.string.contacts), getResources().getString(R.string.diggernet) };
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
