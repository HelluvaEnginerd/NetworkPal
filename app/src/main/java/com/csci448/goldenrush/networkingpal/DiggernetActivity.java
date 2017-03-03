package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hayden on 3/3/17.
 */

public class DiggernetActivity extends AppCompatActivity{

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private Button mCancelButton;

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, DiggernetActivity.class);
        return intent ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diggernet);

        mUsernameEditText = (EditText) findViewById(R.id.enter_username_edit_text);

        mPasswordEditText = (EditText) findViewById(R.id.enter_pw_edit_text);

        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO link to diggernet
                 */
            }
        });

        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  TODO Cancel logging in. Maybe clear text fields & Go back to welcome screen?
                 */
            }
        });

    }


}
