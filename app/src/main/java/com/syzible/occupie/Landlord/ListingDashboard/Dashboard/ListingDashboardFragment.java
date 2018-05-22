package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Objects.Rental;
import com.syzible.occupie.Landlord.ListingDashboard.CreateNewListing.CreateNewListingFragment;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;
import com.syzible.occupie.Common.UI.DividerDecorator;

import java.util.List;

public class ListingDashboardFragment extends Fragment implements ListingDashboardView {

    private ListingDashboardPresenter presenter;
    private ListingDashboardInteractor interactor;
    private RecyclerView recyclerView;

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
        fetchRentals();
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
    public void showRentalListings(List<Rental> rentals) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ListingDashboardAdapter adapter = new ListingDashboardAdapter(rentals);
        recyclerView.setAdapter(adapter);
    }

    public void fetchRentals() {
        interactor.fetchRentals(getActivity(), Endpoints.RENTAL, presenter.onRentalCallback());
    }
}
