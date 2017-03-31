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

import java.util.List;

/**
 * Created by Hayden on 3/25/17.
 */

public class CompanyListFragment extends Fragment{

    private static final String TAG = "CompanyListFragment";
    private static final String EXTRA_POSITION = "com.csci448.goldenrush.networkingpal.companylistfragment.position";

    private RecyclerView mContactRecyclerView;
    private CompaniesAdapter mCompaniesAdapter;
    private int mPosition;

    public static CompanyListFragment newInstance(int position) {
        Log.d(TAG, "newInstance()");
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_POSITION, position);

        CompanyListFragment fragment = new CompanyListFragment();
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
        CompanyLab companyLab = CompanyLab.get(getActivity());
        List<Company> companies = companyLab.getCompanies();

        if (mCompaniesAdapter == null) {
            mCompaniesAdapter = new CompaniesAdapter(companies);
            mContactRecyclerView.setAdapter(mCompaniesAdapter);
        } else {
            mCompaniesAdapter.setCompanies(companies);
            mCompaniesAdapter.notifyDataSetChanged();
        }
    }

    private class CompaniesAdapter extends RecyclerView.Adapter<CompanyListFragment.CompaniesHolder> {
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
            Intent intent = NewCompanyActivity.newIntent(getActivity(), mCompany.getID());
            startActivity(intent);
        }
    }
}
