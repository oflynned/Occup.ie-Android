package com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.syzible.occupie.Common.Objects.HouseShare;
import com.syzible.occupie.Tenant.FindProperty.Common.DividerDecorator;
import com.syzible.occupie.Tenant.FindProperty.Common.PropertyType;
import com.syzible.occupie.R;

import java.util.List;

public class FindHouseShareFragment extends Fragment implements FindHouseShareView {
    private RecyclerView recyclerView;
    private FindHouseSharePresenter findHouseSharePresenter;
    private ProgressBar progressBar;
    private PropertyType propertyType;

    public FindHouseShareFragment() {

    }

    public static FindHouseShareFragment getInstance(PropertyType propertyType) {
        return new FindHouseShareFragment().setPropertyType(propertyType);
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
        if (findHouseSharePresenter == null)
            findHouseSharePresenter = new FindHouseSharePresenterImpl();

        findHouseSharePresenter.attach(this);
        findHouseSharePresenter.getProperties();

        super.onStart();
    }

    @Override
    public void onDestroy() {
        findHouseSharePresenter.detach();
        super.onDestroy();
    }

    @Override
    public void showProperties(List<HouseShare> properties) {
        RecyclerView.Adapter adapter = new PropertyAdapter(properties, getFragmentManager());
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

    private FindHouseShareFragment setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    @Override
    public PropertyType getPropertyType() {
        return propertyType;
    }
}
