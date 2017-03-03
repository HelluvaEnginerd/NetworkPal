package com.csci448.goldenrush.networkingpal;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Nick on 3/3/17.
 */

public class ContactsFragment extends Fragment {

    private static final String TAG = "ContactsFragment";

    private RecyclerView mContactRecyclerView;
    private ContactsFragment.ContactsAdapter mAdapter;

    private class ContactsAdapter extends RecyclerView.Adapter<ContactsFragment.ContactsHolder> {
        private static final String TAG = "ALF:ApplicationAdapter";
        private List<Contact> mContacts;

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
            Contact ctact = mContacts.get(position);
            holder.bindContact(ctact);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount() = " + Integer.toString(mContacts.size()));
            return mContacts.size();
        }
    }

    private class ContactsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "ALF:ApplicationHolder";
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
            Log.d(TAG, "ApplicationHolder()");
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
