package com.syzible.occupie.Tenant.FindProperty.Results.RentalResults;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.syzible.occupie.Common.Objects.Rental;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.FindProperty.Common.DividerDecorator;
import com.syzible.occupie.Tenant.FindProperty.Common.PropertyType;

import java.util.List;

public class FindRentalFragment extends Fragment implements FindRentalView {
    private RecyclerView recyclerView;
    private FindRentalPresenter findRentalPresenter;
    private ProgressBar progressBar;
    private PropertyType propertyType;

    public FindRentalFragment() {

    }

    public static FindRentalFragment getInstance(PropertyType propertyType) {
        return new FindRentalFragment().setPropertyType(propertyType);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_listings, container, false);
        progressBar = view.findViewById(R.id.find_listings_progress_bar);
        setupRecyclerView(view);
        return view;
    }

    @Override
    public void onStart() {
        if (findRentalPresenter == null)
            findRentalPresenter = new FindRentalPresenterImpl();

        findRentalPresenter.attach(this);
        findRentalPresenter.getProperties();

        super.onStart();
    }

    @Override
    public void onDestroy() {
        findRentalPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void showProperties(List<Rental> properties) {
        RecyclerView.Adapter adapter = new PropertyResultsAdapter(properties);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setProgressBarLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void setupRecyclerView(View view) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.properties_holder);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerDecorator(getActivity(), 16));
    }

    private FindRentalFragment setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    @Override
    public PropertyType getPropertyType() {
        return propertyType;
    }
}
