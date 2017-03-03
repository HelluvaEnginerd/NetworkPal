package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class NewApplicationActivity extends AppCompatActivity{
    private static String TAG = "NewApplicationActivity";
    private static final String EXTRA_UUID = "uuid";
    private static final int REQUEST_CONTACT = 1;

    private TextView mCompanyNameTextview;
    private TextView mJobTitleTextview;
    private TextView mCompanyContactTextview;
    private TextView mDateDueTextview;
    private EditText mCompanyNameEditText;
    private EditText mJobTitleEditText;
    private Button mCreateNewButton;
    private Button mChooseExistingButton;
    private EditText mDateDueEditText;
    private CheckBox mCoverLetterCheckBox;
    private CheckBox mResumeCheckBox;
    private CheckBox mSubmittedCheckBox;
    private Button mDone;

    public static Intent newIntent(Context packageContext, UUID id){
        Intent intent = new Intent(packageContext, NewApplicationActivity.class);
        intent.putExtra(EXTRA_UUID, id);
        return intent;
    }

    private void setUp(){
        mCompanyNameTextview = (TextView) findViewById(R.id.company_name_textview);
        mJobTitleTextview= (TextView) findViewById(R.id.job_title_textview);
        mCompanyContactTextview = (TextView) findViewById(R.id.company_contact_textview);
        mDateDueTextview = (TextView) findViewById(R.id.date_due_textview);

        mCompanyNameEditText = (EditText) findViewById(R.id.company_name);
        mJobTitleEditText = (EditText) findViewById(R.id.job_title);
        mDateDueEditText = (EditText) findViewById(R.id.date_due);

        mCreateNewButton = (Button) findViewById(R.id.create_new_button);
        mCreateNewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = NewContactActivity.newIntent(NewApplicationActivity.this);
                startActivity(intent);
            }
        });

        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mChooseExistingButton = (Button) findViewById(R.id.choose_existing_button);
        mChooseExistingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });

        mCoverLetterCheckBox = (CheckBox) findViewById(R.id.cover_letter_checkbox);
        mResumeCheckBox = (CheckBox) findViewById(R.id.resume_checkbox);
        mSubmittedCheckBox = (CheckBox) findViewById(R.id.submitted_checkbox);

        mDone = (Button) findViewById(R.id.done_company_button);
        mDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //THIS WILL NEED TO BE CHANGED
                Intent intent = ApplicationSearchActivity.newIntent(NewApplicationActivity.this);
                startActivity(intent);
            }
        });

/*
        UUID appId = (UUID) getIntent().getSerializableExtra(EXTRA_UUID);
        if (appId != null){
            ApplicationLab appLab = ApplicationLab.get(NewApplicationActivity.this);
            Application app = appLab.getApplication(appId);
            mCompanyNameEditText.setText(app.getCompany());
            mCompanyContact.setText(app.getCompanyContact());
            m
        }
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_application_activity);
        setUp();
    }
}

