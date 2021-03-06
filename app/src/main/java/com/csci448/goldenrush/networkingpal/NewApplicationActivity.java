package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class NewApplicationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, CompanyPickerFragment.CompanyCallbacks,
        ContactPickerFragment.ContactCallbacks, DatePickerFragment.DateCallbacks {
    private static String TAG = "NewApplicationActivity";
    private static final String EXTRA_UUID = "uuid";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_COMPANY = "DialogCompany";
    private static final String DIALOG_CONTACT = "DialogContact";
    private static final String EXTRA_APPLICATION = "com.com.csci448.goldenrush.networkingpal.newapplicationactivity.application";

    private EditText mJobTitleEditText;
    private Button mCreateNewContactButton;
    private Button mChooseExistingContactButton;
    private Button mCreateNewCompanyButton;
    private Button mChooseExistingCompanyButton;
    private Button mDateDue;
    private CheckBox mCoverLetterCheckBox;
    private CheckBox mResumeCheckBox;
    private CheckBox mSubmittedCheckBox;
    private Button mDone;
    private Button mBack;
    private Button delete;
    private boolean newApp;
    private static Intent mLastIntent;

    private Application mApp;

    public static Intent newIntent(Context packageContext, UUID id, Intent i){
        mLastIntent = i;
        Intent intent = new Intent(packageContext, NewApplicationActivity.class);
        if (id != null)
            intent.putExtra(EXTRA_UUID, id);
        return intent;
    }

    private void setUp(){
        Log.d(TAG, "setUp()");
        mJobTitleEditText = (EditText) findViewById(R.id.job_title);
        mJobTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().equals(""))
                    mApp.setJobTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateDue = (Button) findViewById(R.id.date_due_button);
        mDateDue.setText(DateFormat.format("MMM dd, yyyy", mApp.getDateDue()).toString());
        mDateDue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mApp.getDateDue());
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mCreateNewContactButton = (Button) findViewById(R.id.create_new_button);
        mCreateNewContactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = NewApplicationActivity.newIntent(NewApplicationActivity.this, null, mLastIntent);
                Intent intent = NewContactActivity.newIntent(NewApplicationActivity.this, null, i);
                startActivity(intent);
            }
        });

        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mChooseExistingContactButton = (Button) findViewById(R.id.choose_existing_button);
        mChooseExistingContactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                ContactPickerFragment dialog = ContactPickerFragment.newInstance();
                dialog.show(manager, DIALOG_CONTACT);
            }
        });

        mCreateNewCompanyButton = (Button) findViewById(R.id.create_new_company_button);
        mCreateNewCompanyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = NewApplicationActivity.newIntent(NewApplicationActivity.this, null, mLastIntent);
                Intent intent = NewCompanyActivity.newIntent(NewApplicationActivity.this, null, i);
                startActivity(intent);
            }
        });

        mChooseExistingCompanyButton = (Button) findViewById(R.id.choose_existing_company_button);
        mChooseExistingCompanyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                CompanyPickerFragment dialog = CompanyPickerFragment.newInstance();
                dialog.show(manager, DIALOG_COMPANY);
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

        mDone = (Button) findViewById(R.id.done_event_button);
        mDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "Done Button");
                setValues();
                ApplicationLab.get(getApplicationContext()).updateApplication(mApp);
                Intent i = WelcomeActivity.newIntent(NewApplicationActivity.this, 1);
                i.putExtra(EXTRA_APPLICATION, mApp.getId());
                startActivity(i);
            }
        });

        mBack = (Button) findViewById(R.id.back_company_button);
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "back button pressed");
                finish();
            }

        });


        delete = (Button) findViewById(R.id.delete_event_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationLab.get(getApplicationContext()).deleteApplication(mApp.getId());
                finish();
            }
        });

        UUID appId = (UUID) (getIntent().getSerializableExtra(EXTRA_UUID));
        if (appId != null){
            Log.d(TAG, "loading app");
            ApplicationLab appLab = ApplicationLab.get(NewApplicationActivity.this);
            Application app = appLab.getApplication(appId);

            mApp.setID(appId);
            mApp.setJobTitle(app.getJobTitle());
            mJobTitleEditText.setText(app.getJobTitle());
            mApp.setCompanyName(app.getCompanyName());
            mApp.setCompanyContact(app.getCompanyContact());
            mApp.setCoverLetter(app.hasCoverLetter());
            if(app.getCompanyName()!=null)
                mChooseExistingCompanyButton.setText(app.getCompanyName());
            else
                mChooseExistingCompanyButton.setText("Choose Existing");
            if(app.getCompanyName()!=null)
                mCreateNewCompanyButton.setVisibility(View.GONE);
            if(app.getCompanyContact()!=null)
                mChooseExistingContactButton.setText(app.getCompanyContact());
            else
                mChooseExistingContactButton.setText("Choose Existing");
            if(app.getCompanyContact()!=null)
                mCreateNewContactButton.setVisibility(View.GONE);
            if(app.hasCoverLetter()){
                mCoverLetterCheckBox.setChecked(true);
            }

            mApp.setResume(app.hasResume());
            mResumeCheckBox.setChecked(app.hasResume());
            mApp.setSubmitted(app.isSubmitted());
            mSubmittedCheckBox.setChecked(app.isSubmitted());
        }

    }

    public void setValues(){
        mApp.setJobTitle(mJobTitleEditText.getText().toString());
        mApp.setCompanyName(mChooseExistingCompanyButton.getText().toString());
        mApp.setCompanyContact(mChooseExistingContactButton.getText().toString());
        if(mApp.hasCoverLetter()) {
            Log.d(TAG, "has cover letter");
            mApp.setCoverLetter(true);
        }
        if(mApp.hasResume()) {
            mApp.setResume(true);
        }
        if(mApp.isSubmitted()) {
            mApp.setSubmitted(true);
        }
        if (newApp)
            ApplicationLab.get(getApplicationContext()).addApplication(mApp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate()");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent i = getIntent();
        UUID uuid = (UUID) i.getSerializableExtra(EXTRA_UUID);
        if (uuid != null) {
            newApp = false;
            mApp = ApplicationLab.get(getApplicationContext()).getApplication(uuid);
        } else {
            newApp = true;
            mApp = new Application();
        }
        Log.d(TAG, "mApp UUID = " + mApp.getId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_application_activity);
        setUp();
        if (savedInstanceState != null){
            String companyUUIDString = savedInstanceState.getString(NewCompanyActivity.EXTRA_COMPANY);
            Company company = CompanyLab.get(getApplicationContext()).getCompany(UUID.fromString(companyUUIDString));
            mApp.setCompanyName(company.getCompanyName());
            mChooseExistingCompanyButton.setText(company.getCompanyName());
        }
    }

    @Override public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
        Log.d(TAG, "UUID = " + mApp.getId().toString());
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day){
        Log.d(TAG, "Date picked");
        mDateDue.setText(Integer.toString(month) + "/" + Integer.toString(day) + Integer.toString(year));
    }

    @Override
    public void onCompanySelected(Company company){
        Log.d(TAG, "CompanyUUID: " + company.getID().toString());
        mApp.setCompanyName(company.getCompanyName());
        mChooseExistingCompanyButton.setText(company.getCompanyName());
        mCreateNewCompanyButton.setVisibility(View.GONE);
    }

    @Override
    public void onContactSelected(Contact contact){
        mApp.setCompanyContact(contact.getContactName());
        mChooseExistingContactButton.setText(contact.getContactName());
        mCreateNewContactButton.setVisibility(View.GONE);
    }

    @Override
    public void onDateSelected(Date date){
        mApp.setDateDue(date);
        mDateDue.setText(DateFormat.format("MMM dd, yyyy", mApp.getDateDue()).toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Log.d(TAG, "home");
                NavUtils.navigateUpFromSameTask(this);
                WelcomeActivity.setTabPosition(1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

