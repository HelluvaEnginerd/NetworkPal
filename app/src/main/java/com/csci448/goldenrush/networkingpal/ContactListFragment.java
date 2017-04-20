package com.csci448.goldenrush.networkingpal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by Nick on 3/3/17.
 */

public class ContactListFragment extends Fragment {

    /**
     * made tab layout with
     * http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
     */

    private static final String TAG = "ContactListFragment";
    private static final String EXTRA_POSITION = "com.csci448.goldenrush.contactlistfragment.networkingpal.position";

    private RecyclerView mContactRecyclerView;
    private ContactListFragment.ContactsAdapter mContactsAdapter;
    private int mPosition;

    public static ContactListFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_POSITION, position);

        ContactListFragment fragment = new ContactListFragment();
        fragment.setArguments(args);
        return fragment;
    }

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

        if(savedInstanceState!=null) {
            mPosition = savedInstanceState.getInt(EXTRA_POSITION, -1);
            Log.d(TAG, "position = " + mPosition);
        }

        updateUI();

        return view;
    }

    private void updateUI(){
        Log.d(TAG, "updateUI()");
        ContactLab contactLab = ContactLab.get(getActivity());
        List<Contact> contacts = contactLab.getContacts();

        if (mContactsAdapter == null) {
            mContactsAdapter = new ContactsAdapter(contacts);
            mContactRecyclerView.setAdapter(mContactsAdapter);
        } else {
            mContactsAdapter.setContacts(contacts);
            mContactsAdapter.notifyDataSetChanged();
        }
    }

    private class ContactsAdapter extends RecyclerView.Adapter<ContactListFragment.ContactsHolder> {
        private static final String TAG = "CLF:ContactsAdapter";
        private List<Contact> mContacts;


        public ContactsAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }


        @Override
        public ContactListFragment.ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateContactsHolder()");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "inflate list_item_application");
            View view = layoutInflater.inflate(R.layout.list_item_contact, parent, false);
            return new ContactListFragment.ContactsHolder(view);

        }

        @Override public void onBindViewHolder(ContactListFragment.ContactsHolder holder, int position){
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
        private TextView mNameView;
        private TextView mCompanyView;
        private TextView mEmailView;
        private TextView mNumberView;

        public void bindContact(Contact contact) {
            Log.d(TAG, "bindContact()");
            mContact = contact;
            mNameView.setText(mContact.getContactName());
            mCompanyView.setText(mContact.getCompanyName());
            mEmailView.setText((mContact.getEmail()));
            mNumberView.setText(mContact.getPhone());
        }

        public ContactsHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ContactsHolder()");
            itemView.setOnClickListener(this);

            mNameView = (TextView) itemView.findViewById(R.id.list_item_contact_name);
            mCompanyView = (TextView) itemView.findViewById(R.id.list_item_contact_company);
            mEmailView = (TextView) itemView.findViewById(R.id.list_item_contact_email);
            mNumberView = (TextView) itemView.findViewById(R.id.list_item_contact_phone);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick()");
            Intent i = ContactsActivity.newIntent(getActivity(), 1);
            Intent intent = NewContactActivity.newIntent(getActivity(), mContact.getUUID(), i);
            startActivity(intent);
        }
    }
}
