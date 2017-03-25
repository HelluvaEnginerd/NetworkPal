package com.csci448.goldenrush.networkingpal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import android.support.design.widget.FloatingActionButton;


/**
 * Created by Nick on 3/3/17.
 */

public class ContactsFragment extends Fragment {


    /**
     * TODO make tab layout http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
     */

    private static final String TAG = "ContactsFragment";

    private RecyclerView mContactRecyclerView;
    private ContactsFragment.ContactsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        Log.d(TAG, "inflate fragment_list");
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mContactRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        ContactLab contactLab = ContactLab.get(getActivity());
        List<Contact> contacts = contactLab.getContacts();

        if(mAdapter==null){
            mAdapter = new ContactsAdapter(contacts);
            mContactRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.setContacts(contacts);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ContactsAdapter extends RecyclerView.Adapter<ContactsFragment.ContactsHolder> {
        private static final String TAG = "CLF:ContactsAdapter";
        private List<Contact> mContacts;
        private FloatingActionButton mFABadd;
        //NOT SURE WHERE TO PUT THIS

        public ContactsAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactsFragment.ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateContactsHolder()");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "inflate list_item_application");
            View view = layoutInflater.inflate(R.layout.list_item_application, parent, false);
            return new ContactsFragment.ContactsHolder(view);

        }

        @Override public void onBindViewHolder(ContactsFragment.ContactsHolder holder, int position){
            Log.d(TAG, "onBindViewHolder()");
            Contact contact = mContacts.get(position);
            holder.bindContact(contact);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount() = " + Integer.toString(mContacts.size()));
            return mContacts.size();
        }

        public void setContacts(List<Contact> contacts){
            mContacts = contacts;
        }
    }

    private class ContactsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "CLF:ContactsHolder";
        private Contact mContact;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mCompanyTextView;
        private TextView mContactTextView;

        public void bindContact(Contact contact) {
            Log.d(TAG, "bindContact()");
            mContact = contact;
//            mTitleTextView.setText(mContact.getName() + " " + mApp.getJobTitle());
//            mDateTextView.setText(formatter.format(mApp.getDateDue()));
//            mCompanyTextView.setText(mApp.getCompany());
//            mContactTextView.setText(mApp.getCompanyContact());
        }

        public ContactsHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ContactsHolder()");
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_application_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_application_date_text_view);
            mCompanyTextView = (TextView) itemView.findViewById(R.id.list_item_application_company_text_view);
            mContactTextView = (TextView) itemView.findViewById(R.id.list_item_application_contact_text_view);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick()");
        }
    }

}
