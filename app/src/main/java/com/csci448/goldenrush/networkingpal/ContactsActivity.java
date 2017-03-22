package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

/**
 * Created by Nick on 3/2/17.
 */

public class ContactsActivity extends FragmentActivity{

    private static String TAG = "ContactsActivity";
    private FloatingActionButton mFABaddContact;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, ContactsActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_search);

        mFABaddContact = (FloatingActionButton) findViewById(R.id.fab_add_contacts);

        mFABaddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB add application");
                Intent intent = NewContactActivity.newIntent(ContactsActivity.this);
                startActivity(intent);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_list_host);

        if (fragment == null){
            fragment = new ApplicationListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_list_host, fragment)
                    .commit();
        }
    }

}
