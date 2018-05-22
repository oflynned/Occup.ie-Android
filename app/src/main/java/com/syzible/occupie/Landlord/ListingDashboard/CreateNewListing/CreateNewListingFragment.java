package com.syzible.occupie.Landlord.ListingDashboard.CreateNewListing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateNewListingFragment extends Fragment {

    public CreateNewListingFragment() {
    }

    public static CreateNewListingFragment getInstance() {
        return new CreateNewListingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
