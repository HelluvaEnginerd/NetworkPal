package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Sarah on 2/28/2017.
 * Activity created when creating a new event from the calendar
 */

public class NewEventActivity extends AppCompatActivity {
    private static final String TAG = "NewEventActivity";
    //all widgets
    EditText name;
    EditText date;
    EditText company;
    EditText details;
    Button create;

    //Keys for save instance state
    private static final String EVENT_NAME = "EventNameString";
    private static final String DATE = "DateString";
    private static final String COMPANY_NAME = "CompanyString";
    private static final String DETAILS ="DetailsString";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG,"onCreate Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_activity);
        setUpWidgets();

        //reload if there is a saved state
        if(savedInstanceState!=null)
        {
            name.setText(savedInstanceState.getString(EVENT_NAME));
            date.setText(savedInstanceState.getString(DATE));
            company.setText(savedInstanceState.getString(COMPANY_NAME));
            details.setText(savedInstanceState.getString(DETAILS));
        }
    }

    private void setUpWidgets(){
        name = (EditText) findViewById(R.id.event_box);
        name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                name.setText(R.string.empty_text);
            }
        });

        date= (EditText) findViewById(R.id.time_box);
        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                date.setText(R.string.empty_text);
            }
        });

        company = (EditText) findViewById(R.id.company_box);
        company.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                company.setText(R.string.empty_text);
            }
        });
        details = (EditText) findViewById(R.id.details_box);
        details.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                details.setText(R.string.empty_text);
            }
        });

        create = (Button) findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: make the activity
                finish(); //exit out of activity
            }
        });
        //Todo: Add all listeners

    }
    public static Intent newIntent(Context packageContext){
        Intent i = new Intent(packageContext, NewEventActivity.class);
        //TODO: Add any extras you may need when creating an event

        return i ;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSaveInstanceStateCalled");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(EVENT_NAME, name.getText().toString());
        savedInstanceState.putString(DATE, date.getText().toString());
        savedInstanceState.putString(COMPANY_NAME, company.getText().toString());
        savedInstanceState.putString(DETAILS, details.getText().toString());
    }
}
