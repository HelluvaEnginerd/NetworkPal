package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sarah on 2/28/2017.
 * Displays calender to hold all event activities
 */

public class CalendarActivity extends AppCompatActivity {
    Button newEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        setUpWidgets();
    }

    private void setUpWidgets(){

        newEventButton = (Button) findViewById(R.id.new_event_button);

        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = NewEventActivity.newIntent(CalendarActivity.this);
                startActivity(i);

            }
        });

    }
    public static Intent newIntent(Context packageContext){
        Intent i = new Intent(packageContext, CalendarActivity.class);
        //TODO: Add any extras you may need when creating an event

        return i ;
    }

    //I haven't actually figured out all of the bar stuff on the top... but I think we should add that... I will get to that lab eventually
}
