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
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hayden on 3/25/17.
 */

public class NewCompanyActivity extends AppCompatActivity{
    private static String TAG = "NewCompanyActivity";
    private static final String EXTRA_UUID = "uuid";

    private Company mCompany;
    private RecentAction mRecentAction;
    private boolean keepCompany = false;

    private EditText mCompanyEditText;
    private EditText mPhoneEditText;
    private EditText mAddressEditText;
    private Button mDoneButton;


    public static Intent newIntent(Context packageContext, UUID uuid){
        Log.d(TAG, "newIntent()");
        Intent intent = new Intent(packageContext, NewCompanyActivity.class);
        intent.putExtra(EXTRA_UUID, uuid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.new_company_activity);
        mCompany = new Company();
        setUp();
    }

    private void setUp(){
        Log.d(TAG, "setup()");

        mCompanyEditText = (EditText) findViewById(R.id.company_name);
        mCompanyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    mCompany.setCompanyName(s.toString());
                    keepCompany = true;
                }else {
                    keepCompany = false;
                }
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
                if (s != null) {
                    mCompany.setAddress(s.toString());
                    keepCompany = true;
                } else {
                    keepCompany = false;
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
                    mCompany.setPhoneNumber(s.toString());
                    keepCompany = true;
                } else {
                    keepCompany = false;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDoneButton = (Button) findViewById(R.id.done_company_button);
        mDoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "doneButton Clicked");
                /**
                 * TODO figure out how to make this either go to contacts or back to new application/contact activity. Back to where it came from basically
                 * Currently goes back to company recycler list in contacts activity
                 */

                if (keepCompany) {
                    Log.d(TAG, "updating Company");
                    Toast.makeText(getApplicationContext(), mCompany.getCompanyName() + " updated in database", Toast.LENGTH_SHORT).show();
                    CompanyLab.get(getApplicationContext()).updateCompany(mCompany);
                    mRecentAction = new RecentAction("Company", "FILL", new Date(), mCompany.getCompanyName());
                    RecentActionLab.get(getApplicationContext()).addRecentActivity(mRecentAction);
                } else {
                    Log.d(TAG, "Empty Company - discard");
                    Toast.makeText(getApplicationContext(), "Blank Company discarded", Toast.LENGTH_SHORT).show();
                    /**
                     * TODO remove empty companies (EMPTY_FIELD for everything but UUID).
                     */
                }
                Intent intent = ContactsActivity.newIntent(NewCompanyActivity.this, 1);
                startActivity(intent);
            }
        });

        UUID companyID = (UUID) getIntent().getSerializableExtra(EXTRA_UUID);
        if (companyID != null){
            CompanyLab companyLab = CompanyLab.get(NewCompanyActivity.this);
            Company company = companyLab.getCompany(companyID);
            mPhoneEditText.setText(company.getPhoneNumber());
            mAddressEditText.setText(company.getAddress());
            mCompanyEditText.setText(company.getCompanyName());
        }

    }

    @Override
    public void  onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
        CompanyLab.get(getApplicationContext()).updateCompany(mCompany);
        RecentActionLab.get(getApplicationContext()).updateRecentAction(mRecentAction);
    }

}
