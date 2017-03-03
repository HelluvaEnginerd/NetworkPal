package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class NewContactActivity extends AppCompatActivity{
    private static String TAG = "NewContactActivity";
    private TextView mContactNameTextview;
    private TextView mCompanyNameTextview;
    private TextView mEmailTextview;
    private TextView mPhoneTextview;
    private TextView mTitleNameTextview;
    private TextView mBusinessCardTextview;
    private EditText mContactNameEditText;
    private EditText mCompanyNameEditText;
    private EditText mEmailEditText;
    private EditText mPhoneEditText;
    private EditText mTitleEditText;
    private ImageButton mBusinessCardButton;
    private Button mDone;

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, NewContactActivity.class);
        return intent;
    }

    private void setUp(){
        mContactNameTextview = (TextView) findViewById(R.id.contact_name_textview);
        mCompanyNameTextview = (TextView) findViewById(R.id.company_name_textview);
        mEmailTextview= (TextView) findViewById(R.id.email_textview);
        mPhoneTextview = (TextView) findViewById(R.id.phone_textview);
        mTitleNameTextview = (TextView) findViewById(R.id.title_textview);
        mBusinessCardTextview = (TextView) findViewById(R.id.business_card_textview);

        mCompanyNameEditText = (EditText) findViewById(R.id.company_name);
        mContactNameEditText = (EditText) findViewById(R.id.contact_name);
        mEmailEditText = (EditText) findViewById(R.id.email);
        mPhoneEditText = (EditText) findViewById(R.id.phone);
        mTitleEditText = (EditText) findViewById(R.id.title_edittext) ;

        mBusinessCardButton = (ImageButton) findViewById(R.id.business_card_imageButton);
        mBusinessCardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //link to camera
            }
        });

        mDone = (Button) findViewById(R.id.done_company_button);
        mDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //THIS WILL NEED TO BE CHANGED
                Intent intent = NewApplicationActivity.newIntent(NewContactActivity.this, null);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contact_activity);
        setUp();
    }
}

