package com.csci448.goldenrush.networkingpal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hayden on 4/1/17.
 */

public class CompanyPickerFragment extends DialogFragment {
    private static final String TAG = "CompanyPickerFragment";

    private RecyclerView mRecyclerView;
    private CompaniesAdapter mCompaniesAdapter;

    public static CompanyPickerFragment newInstance(){
        Log.d(TAG, "newInstance()");

        CompanyPickerFragment fragment = new CompanyPickerFragment();
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
                .setTitle(R.string.company_picker_title)
                .create();
    }

    private void updateUI(){
        Log.d(TAG, "updateUI()");
        CompanyLab companyLab = CompanyLab.get(getActivity());
        List<Company> companies = companyLab.getCompanies();

        if (mCompaniesAdapter == null) {
            mCompaniesAdapter = new CompaniesAdapter(companies);
            mRecyclerView.setAdapter(mCompaniesAdapter);
        } else {
            mCompaniesAdapter.setCompanies(companies);
            mCompaniesAdapter.notifyDataSetChanged();
        }
    }

    private class CompaniesAdapter extends RecyclerView.Adapter<CompaniesHolder> {
        private List<Company> mCompanies;
        public CompaniesAdapter(List<Company> companies) {
            mCompanies = companies;
        }

        @Override
        public CompaniesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateContactsHolder()");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d(TAG, "inflate list_item_application");
            View view = layoutInflater.inflate(R.layout.list_item_company, parent, false);
            return new CompaniesHolder(view);
        }

        @Override
        public void onBindViewHolder(CompaniesHolder holder, int position){
            Log.d(TAG, "onBindViewHolder()");
            Company company = mCompanies.get(position);
            holder.bindCompany(company);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount() = " + Integer.toString(mCompanies.size()));
            return mCompanies.size();
        }

        public void setCompanies(List<Company> companies) {mCompanies = companies;}
    }

    private class CompaniesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "CLF:CompaniesHolder";
        private Company mCompany;
        private TextView mCompanyNameView;
        private TextView mCompanyPhoneView;
        private TextView mCompanyAddressView;

        public void bindCompany(Company company) {
            Log.d(TAG, "bindCompany()");

            mCompany = company;
            mCompanyNameView.setText(mCompany.getCompanyName());
            mCompanyPhoneView.setText(mCompany.getPhoneNumber());
            mCompanyAddressView.setText((mCompany.getAddress()));
        }

        public CompaniesHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ContactsHolder()");
            itemView.setOnClickListener(this);

            mCompanyNameView = (TextView) itemView.findViewById(R.id.list_item_company_name);
            mCompanyPhoneView= (TextView) itemView.findViewById(R.id.list_item_company_phone);
            mCompanyAddressView = (TextView) itemView.findViewById(R.id.list_item_company_address);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick()");
            /**
             * TODO use callback to go back to activity w/info
             */
        }
    }
}
