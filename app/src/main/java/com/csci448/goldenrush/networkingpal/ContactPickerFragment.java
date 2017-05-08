package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hayden on 4/2/17.
 */

public class ContactPickerFragment extends DialogFragment {
    private static final String TAG = "ContactPickerFragment";

    private RecyclerView mRecyclerView;
    private ContactsAdapter mContactAdapter;
    private ContactCallbacks mContactCallbacks;

    public interface ContactCallbacks {
        void onContactSelected(Contact contact);
    }

    @Override
    public void onAttach(Activity activity){
        Log.d(TAG, "onAttach()");
        super.onAttach(activity);
        mContactCallbacks = (ContactCallbacks) activity;
    }

    @Override
    public void onDetach(){
        Log.d(TAG, "onDetach()");
        super.onDetach();
        mContactCallbacks = null;
    }

    public static ContactPickerFragment newInstance(){
        Log.d(TAG, "newInstance()");
        ContactPickerFragment fragment = new ContactPickerFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Log.d(TAG, "onCreateDialog");

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_list, null);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.contact_picker_title)
                .create();
    }

    private void updateUI(){
        Log.d(TAG, "updateUI()");
        ContactLab contactLab = ContactLab.get(getActivity());
        List<Contact> contacts = contactLab.getContacts();

        if (mContactAdapter == null) {
            mContactAdapter = new ContactsAdapter(contacts);
            mRecyclerView.setAdapter(mContactAdapter);
        } else {
            mContactAdapter.setContacts(contacts);
            mContactAdapter.notifyDataSetChanged();
        }
    }

    private class ContactsAdapter extends RecyclerView.Adapter<ContactsHolder> {
        private static final String TAG = "CLF:ContactsAdapter";
        private List<Contact> mContacts;


        public ContactsAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }


        @Override
        public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateContactsHolder()");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "inflate list_item_application");
            View view = layoutInflater.inflate(R.layout.list_item_contact, parent, false);
            return new ContactsHolder(view);

        }

        @Override public void onBindViewHolder(ContactsHolder holder, int position){
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
            mContactCallbacks.onContactSelected(mContact);
            dismiss();
        }
    }

}
