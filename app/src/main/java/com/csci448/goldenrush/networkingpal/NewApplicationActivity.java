package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class NewApplicationActivity extends AppCompatActivity{
    private static String TAG = "NewApplicationActivity";
    private static final String EXTRA_UUID = "uuid";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final String DIALOG_DATE = "DialogDate";

    private TextView mCompanyNameTextview;
    private TextView mJobTitleTextview;
    private TextView mCompanyContactTextview;
    private TextView mDateDueTextview;
    private EditText mCompanyNameEditText;
    private EditText mJobTitleEditText;
    private Button mCreateNewButton;
    private Button mChooseExistingButton;
    private Button mDateDue;
    private CheckBox mCoverLetterCheckBox;
    private CheckBox mResumeCheckBox;
    private CheckBox mSubmittedCheckBox;
    private Button mDone;

    private Application mApp;

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
        mCompanyNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mApp.setCompany(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mJobTitleEditText = (EditText) findViewById(R.id.job_title);
        mJobTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mApp.setJobTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateDue = (Button) findViewById(R.id.date_due_button);
        mDateDue.setText(mApp.getDateDue().toString());
        mDateDue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mApp.getDateDue());
                //NO CLUE WHAT TO DO HERE SINCE THIS IS AN ACTIVITY - THIS IS THE PROBLEM WITH THE DATE
                //dialog.setTargetFragment(ApplicationSearchActivity.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

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
        mCoverLetterCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mApp.setCoverLetter(isChecked);
            }
        });
        mResumeCheckBox = (CheckBox) findViewById(R.id.resume_checkbox);
        mResumeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mApp.setResume(isChecked);
            }
        });

        mSubmittedCheckBox = (CheckBox) findViewById(R.id.submitted_checkbox);
        mSubmittedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mApp.setSubmitted(isChecked);
            }
        });

        mDone = (Button) findViewById(R.id.done_company_button);
        mDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //ApplicationLab.addApplcation(mApp);
                /**
                 * goes to application list view
                 */
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
        mApp = new Application();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_application_activity);
        setUp();
    }

    @Override public void onPause(){
        super.onPause();
        Log.d("pause", "onPause");
        ApplicationLab.get(this).updateApplication(mApp);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            Log.d("arg", date.toString());
            mApp.setDateDue(date);
            mDateDue.setText(mApp.getDateDue().toString());
        }
    }
}

