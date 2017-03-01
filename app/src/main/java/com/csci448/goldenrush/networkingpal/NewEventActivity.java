package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Sarah on 2/28/2017.
 * Activity created when creating a new event from the calendar
 */

public class NewEventActivity extends AppCompatActivity {
    //all widgets
    EditText name;
    EditText date;
    EditText company;
    EditText details;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_activity);
        setUpWidgets();
    }

    private void setUpWidgets(){
        name = (EditText) findViewById(R.id.event_box);
        date= (EditText) findViewById(R.id.time_box);
        company = (EditText) findViewById(R.id.company_box);
        details = (EditText) findViewById(R.id.details_box);
        create = (Button) findViewById(R.id.create_button);

        //Todo: Add all listeners

    }
    public static Intent newIntent(Context packageContext){
        Intent i = new Intent(packageContext, NewEventActivity.class);
        //TODO: Add any extras you may need when creating an event

        return i ;
    }
}
