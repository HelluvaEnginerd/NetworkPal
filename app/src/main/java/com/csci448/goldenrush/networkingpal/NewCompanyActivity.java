package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.UUID;

/**
 * Created by Hayden on 3/25/17.
 */

public class NewCompanyActivity extends AppCompatActivity{
    private static String TAG = "NewCompanyActivity";
    private static final String EXTRA_UUID = "uuid";

    private Company mCompany;

    private EditText mCompanyEditText;
    private EditText mPhoneEditText;
    private EditText mAddressEditText;
    private Button mDoneButton;


    public static Intent newIntent(Context packageContext, UUID uuid){
        Intent intent = new Intent(packageContext, NewCompanyActivity.class);
        intent.putExtra(EXTRA_UUID, uuid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_company_activity);
        setUp();
    }

    private void setUp(){

        mCompanyEditText = (EditText) findViewById(R.id.company_name);
        mCompanyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCompany.setCompanyName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAddressEditText = (EditText) findViewById(R.id.address);
        mAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCompany.setAddress(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPhoneEditText = (EditText) findViewById(R.id.phone);
        mPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCompany.setPhoneNumber(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDoneButton = (Button) findViewById(R.id.done_company_button);
        mDoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /**
                 * TODO figure out how to make this either go to contacts or back to new application/contact activity. Back to where it came from basically
                 */
                Intent intent = ContactsActivity.newIntent(NewCompanyActivity.this);
                startActivity(intent);
            }
        });
    }



}
