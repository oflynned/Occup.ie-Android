package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.occupie.R;

public class ListingDashboardErrorFragment extends Fragment {
    public ListingDashboardErrorFragment() {
    }

    public static ListingDashboardErrorFragment getInstance() {
        return new ListingDashboardErrorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_landlord_dashboard_listings_error, container, false);
    }
}
