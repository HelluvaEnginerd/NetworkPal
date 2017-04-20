package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Hayden on 3/3/17.
 */

public class DiggernetActivity extends AppCompatActivity{

    private static final String TAG = "DiggernetActivity";

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private Button mCancelButton;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    public static Intent newIntent(Context packageContext){
        Log.d(TAG, "newIntent()");
        Intent intent = new Intent(packageContext, DiggernetActivity.class);
        return intent ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_diggernet);

        //******* Drawer things *******
        mDrawerList = (ListView)findViewById(R.id.welcome_navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.welcome_drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //******* Drawer things *******


        mUsernameEditText = (EditText) findViewById(R.id.enter_username_edit_text);

        mPasswordEditText = (EditText) findViewById(R.id.enter_pw_edit_text);

        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO link to diggernet
                 * TODO remember login?
                 */
            }
        });

        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  Cancels login, goes back to welcome activity
                 */
                Intent i = WelcomeActivity.newIntent(DiggernetActivity.this, 0);
                startActivity(i);
            }
        });

    }

    private void addDrawerItems() {
        String[] activityArray = { getResources().getString(R.string.welcome), getResources().getString(R.string.applications), getResources().getString(R.string.calendar), getResources().getString(R.string.contacts)};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = WelcomeActivity.newIntent(getApplicationContext(), 0);
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
                    case 3: Toast.makeText(getApplicationContext(), "Contacts", Toast.LENGTH_SHORT).show();
                        Intent intent3 = ContactsActivity.newIntent(getApplicationContext(), 0);
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
