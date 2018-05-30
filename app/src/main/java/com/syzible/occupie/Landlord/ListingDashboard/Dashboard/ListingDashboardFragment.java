package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Objects.Property;
import com.syzible.occupie.Common.Persistence.LocalPrefs;
import com.syzible.occupie.Landlord.ListingDashboard.CreateNewListing.CreateNewListingFragment;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;
import com.syzible.occupie.Tenant.Applications.ErrorFragment;

import java.util.ArrayList;
import java.util.List;

public class ListingDashboardFragment extends Fragment implements ListingDashboardView {

    private ListingDashboardPresenter presenter;
    private ListingDashboardInteractor interactor;
    private ListingDashboardAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public ListingDashboardFragment() {
    }

    public static ListingDashboardFragment getInstance() {
        return new ListingDashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_dashboard, container, false);
        recyclerView = view.findViewById(R.id.dashboard_recycler_view);
        progressBar = view.findViewById(R.id.dashboard_progress_bar);

        FloatingActionButton createNewListingFab = view.findViewById(R.id.create_new_listing);
        createNewListingFab.setOnClickListener(v -> onCreateNewListingClick());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter == null)
            presenter = new ListingDashboardPresenterImpl();

        if (interactor == null)
            interactor = new ListingDashboardInteractorImpl();

        presenter.attach(this);
        progressBar.setVisibility(View.VISIBLE);
        adapter = new ListingDashboardAdapter(new ArrayList<>());
        fetchListings();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void onCreateNewListingClick() {
        MainActivity.setFragment(getFragmentManager(), CreateNewListingFragment.getInstance());
    }

    @Override
    public void showPropertyListings(List<Property> active, List<Property> paused, List<Property> expired) {
        progressBar.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager activeLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(activeLayoutManager);

        List<Property> properties = new ArrayList<>();
        properties.addAll(active);
        properties.addAll(paused);
        properties.addAll(expired);
        adapter = new ListingDashboardAdapter(properties);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setErrorLayout() {
        MainActivity.setFragment(getFragmentManager(), ErrorFragment.getInstance());
    }

    @Override
    public void setEmptyLayout() {
        MainActivity.setFragment(getFragmentManager(), ListingDashboardNoListingsFragment.getInstance());
    }

    public void fetchListings() {
        String endpoint = String.format("%s/%s/listings",
                Endpoints.LANDLORD,
                LocalPrefs.getStringPref(getActivity(), LocalPrefs.Pref.landlord_id)
        );
        interactor.fetchListings(getActivity(), endpoint, presenter.onListingCallback());
    }
}
