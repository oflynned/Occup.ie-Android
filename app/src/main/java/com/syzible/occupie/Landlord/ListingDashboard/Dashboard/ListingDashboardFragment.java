package com.syzible.occupie.Landlord.ListingDashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.occupie.R;

public class ListingDashboardFragment extends Fragment implements ListingDashboardView {

    private View view;
    private ListingDashboardPresenter presenter;
    private ListingDashboardInteractor interactor;

    public ListingDashboardFragment() {
    }

    public static ListingDashboardFragment getInstance() {
        return new ListingDashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_landlord_dashboard, container, false);

        return view;
    }
}
