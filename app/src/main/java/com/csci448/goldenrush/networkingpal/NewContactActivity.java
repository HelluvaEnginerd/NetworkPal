package com.csci448.goldenrush.networkingpal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class NewContactActivity extends AppCompatActivity{
    private static String TAG = "NewContactActivity";
    private static final String EXTRA_UUID = "uuid";
    public static final String EXTRA_CONTACT = "com.csci448.goldenrush.networkingpal.newcompanyactivity.contact";

    private RecentAction mRecentAction;
    private boolean keepContact = false;

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
    private ImageView mBusinessCardPhoto;
    private Button mDone;
    private Contact mContact;
    private static Intent mLastIntent;

    public static Intent newIntent(Context packageContext, UUID uuid, Intent i){
        Log.d(TAG, "newIntent()");
        mLastIntent = i;
        Intent intent = new Intent(packageContext, NewContactActivity.class);
        intent.putExtra(EXTRA_UUID, uuid);
        return intent;
    }

    private void setUp(){
        Log.d(TAG, "setUp()");
        mContactNameTextview = (TextView) findViewById(R.id.contact_name_textview);
        mCompanyNameTextview = (TextView) findViewById(R.id.company_name_textview);
        mEmailTextview= (TextView) findViewById(R.id.email_textview);
        mPhoneTextview = (TextView) findViewById(R.id.phone_textview);
        mTitleNameTextview = (TextView) findViewById(R.id.title_textview);
        mBusinessCardTextview = (TextView) findViewById(R.id.business_card_textview);

        mCompanyNameEditText = (EditText) findViewById(R.id.company_name);
        mCompanyNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    mContact.setCompanyName(s.toString());
                    keepContact = true;
                }else {
                    keepContact = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mContactNameEditText = (EditText) findViewById(R.id.contact_name);
        mContactNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    mContact.setContactName(s.toString());
                    keepContact = true;
                }else {
                    keepContact = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEmailEditText = (EditText) findViewById(R.id.email);
        mEmailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    mContact.setEmail(s.toString());
                    keepContact = true;
                }else {
                    keepContact = false;
                }
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
                if (s != null) {
                    mContact.setPhone(s.toString());
                    keepContact = true;
                }else {
                    keepContact = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTitleEditText = (EditText) findViewById(R.id.title_edittext) ;
        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    mContact.setTitle(s.toString());
                    keepContact = true;
                }else {
                    keepContact = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBusinessCardPhoto = (ImageView) findViewById(R.id.business_card_photo);

        mBusinessCardButton = (ImageButton) findViewById(R.id.business_card_camera);
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

                /*
                mRecentAction = new RecentAction("Contact", "Contact Name", new Date(), "Contact company name");
                RecentActionLab.get(getApplicationContext()).addRecentActivity(mRecentAction);
                */

                Log.d(TAG, "doneButton Clicked");

                if (keepContact) {
                    Log.d(TAG, "updating Company");
                    Toast.makeText(getApplicationContext(), mContact.getContactName() + " updated in database", Toast.LENGTH_SHORT).show();
                    ContactLab.get(getApplicationContext()).updateContact(mContact);

                    /*
                    mRecentAction = new RecentAction("Company", "FILL", new Date(), mCompany.getCompanyName());
                    RecentActionLab.get(getApplicationContext()).addRecentActivity(mRecentAction);
                    */

                } else {
                    Log.d(TAG, "Empty Company - discard");
                    Toast.makeText(getApplicationContext(), "Blank Company discarded", Toast.LENGTH_SHORT).show();
                }
                if(mLastIntent!=null)
                    startActivity(mLastIntent);
                else{
                    Intent i = WelcomeActivity.newIntent(NewContactActivity.this);
                }
                mLastIntent.putExtra(EXTRA_CONTACT, mContact.getUUID());

                startActivity(mLastIntent);
            }
        });
/*
        UUID contactID = (UUID) getIntent().getSerializableExtra(EXTRA_UUID);
        if (contactID != null){
            ContactLab contactLab = ContactLab.get(NewContactActivity.this);
            Contact contact = contactLab.getContact(contactID);
            mContact.setUUID(contact.getUUID());
            mPhoneEditText.setText(contact.getPhone());
            mEmailEditText.setText(contact.getEmail());
            mTitleEditText.setText(contact.getTitle());
            mCompanyNameEditText.setText(contact.getCompanyName());
            mContactNameEditText.setText(contact.getContactName());
            }
        }
*/
    }

    @Override
    public void onPause(){
        super.onPause();

        Log.d(TAG, "onPause()");
        /**
         * TODO add once Contact DB is complete
         * ContactLab.get(getApplicationContext())
         */
        //if (keepContact)
            //RecentActionLab.get(getApplicationContext()).updateRecentAction(mRecentAction);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.new_contact_activity);
        setUp();
        mContact = new Contact();
    }
}

