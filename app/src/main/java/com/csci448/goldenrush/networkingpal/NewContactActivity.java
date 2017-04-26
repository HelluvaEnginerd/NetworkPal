package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.File;
import java.util.UUID;

/**
 * Created by ddunmire on 2/27/2017.
 */

public class NewContactActivity extends AppCompatActivity implements CompanyPickerFragment.CompanyCallbacks{
    private static String TAG = "NewContactActivity";
    private static final String EXTRA_UUID = "uuid";
    public static final String EXTRA_CONTACT = "com.csci448.goldenrush.networkingpal.newcompanyactivity.contact";
    private static final String DIALOG_COMPANY = "DialogCompany";
    private static final int REQUEST_PHOTO = 2;


    private boolean keepContact = false;

    private TextView mContactNameTextview;
    private TextView mCompanyNameTextview;
    private TextView mEmailTextview;
    private TextView mPhoneTextview;
    private TextView mTitleNameTextview;
    private TextView mBusinessCardTextview;
    private EditText mContactNameEditText;
    private Button mChooseExisting;
    private Button mCreateNew;
    private EditText mEmailEditText;
    private EditText mPhoneEditText;
    private EditText mTitleEditText;
    private ImageButton mBusinessCardButton;
    private ImageView mBusinessCardPhoto;
    private Button mDone;
    private Contact mContact;
    private static Intent mLastIntent;
    private Button mBack;
    private File mPhotoFile;

    public static Intent newIntent(Context packageContext, UUID uuid, Intent i){
        Log.d(TAG, "newIntent()");
        mLastIntent = i;
        Intent intent = new Intent(packageContext, NewContactActivity.class);
        intent.putExtra(EXTRA_UUID, uuid);
        return intent;
    }

    @Override
    public void onCompanySelected(Company company){
        Log.d(TAG, "CompanyUUID: " + company.getID().toString());
        mContact.setCompanyName(company.getCompanyName());
        mChooseExisting.setText(company.getCompanyName());
        mCreateNew.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.new_contact_activity);
        mContact = new Contact();
        setUp();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_PHOTO){
            updatePhotoView();
        }
    }

    private void setUp(){
        Log.d(TAG, "setUp()");
        mContactNameTextview = (TextView) findViewById(R.id.contact_name_textview);
        mCompanyNameTextview = (TextView) findViewById(R.id.company_name_textview);
        mEmailTextview= (TextView) findViewById(R.id.email_textview);
        mPhoneTextview = (TextView) findViewById(R.id.phone_textview);
        mTitleNameTextview = (TextView) findViewById(R.id.title_textview);
        mBusinessCardTextview = (TextView) findViewById(R.id.business_card_textview);

        mCreateNew = (Button) findViewById(R.id.create_new_company_button2);
        mCreateNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = NewContactActivity.newIntent(NewContactActivity.this, UUID.randomUUID(), null);
                Intent intent = NewCompanyActivity.newIntent(NewContactActivity.this, null, i);
                startActivity(intent);
            }
        });

        mChooseExisting = (Button) findViewById(R.id.choose_existing_company_button2);
        mChooseExisting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();
                CompanyPickerFragment dialog = CompanyPickerFragment.newInstance();
                dialog.show(manager, DIALOG_COMPANY);
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



        final UUID contactID = (UUID) getIntent().getSerializableExtra(EXTRA_UUID);

        mDone = (Button) findViewById(R.id.done_company_button);
        mDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "doneButton Clicked");

                if (keepContact && contactID == null) {
                    Log.d(TAG, "updating Company");
                    Toast.makeText(getApplicationContext(), mContact.getContactName() + " updated in database", Toast.LENGTH_SHORT).show();
                    ContactLab.get(getApplicationContext()).addContact(mContact);

                } else {
                    Log.d(TAG, "Empty Company - discard");
                    Toast.makeText(getApplicationContext(), "Blank Company discarded", Toast.LENGTH_SHORT).show();
                }
                mLastIntent.putExtra(EXTRA_CONTACT, mCompanyNameTextview.getId());
                startActivity(mLastIntent);
            }
        });

        mBack = (Button) findViewById(R.id.back_company_button);
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "back button pressed");
                //Intent i = WelcomeActivity.newIntent(NewContactActivity.this, 3);
                //startActivity(mLastIntent);
                finish();
            }

        });

        if (contactID != null){
            ContactLab contactLab = ContactLab.get(NewContactActivity.this);
            Contact contact = contactLab.getContact(contactID);
            mContact.setUUID(contact.getUUID());
            mPhoneEditText.setText(contact.getPhone());
            mEmailEditText.setText(contact.getEmail());
            mTitleEditText.setText(contact.getTitle());
            mChooseExisting.setText(contact.getCompanyName());
            mCreateNew.setVisibility(View.GONE);
            mContactNameEditText.setText(contact.getContactName());
            mPhotoFile = contactLab.getPhotoFile(mContact);
            }

        mBusinessCardPhoto = (ImageView) findViewById(R.id.business_card_photo);
        updatePhotoView();

        mBusinessCardButton = (ImageButton) findViewById(R.id.business_card_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null;
        mBusinessCardButton.setEnabled(canTakePhoto);

        if(canTakePhoto){
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mBusinessCardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

    }

    private void updatePhotoView(){
        if(mPhotoFile == null || !mPhotoFile.exists()){
            mBusinessCardPhoto.setImageDrawable(null);
        } else{
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), NewContactActivity.this);
            mBusinessCardPhoto.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onPause(){
        super.onPause();

        Log.d(TAG, "onPause()");
        if (keepContact)
            ContactLab.get(getApplicationContext()).updateContact(mContact);
        /**
         * TODO add once Contact DB is complete
         * ContactLab.get(getApplicationContext())
         */
        //if (keepContact)
            //RecentActionLab.get(getApplicationContext()).updateRecentAction(mRecentAction);
    }


}

