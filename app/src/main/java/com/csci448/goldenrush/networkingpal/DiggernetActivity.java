package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hayden on 3/3/17.
 */

public class DiggernetActivity extends AppCompatActivity{

    private static final String TAG = "DiggernetActivity";

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private Button mCancelButton;

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
                Intent i = WelcomeActivity.newIntent(DiggernetActivity.this);
                startActivity(i);
            }
        });

    }


}
