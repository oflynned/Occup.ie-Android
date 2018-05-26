package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import com.syzible.occupie.Common.Mvp;
import com.syzible.occupie.Common.Objects.Property;
import com.syzible.occupie.Common.Objects.Rental;

import java.util.List;

public interface ListingDashboardView extends Mvp.IView {
    void onCreateNewListingClick();

    void showPropertyListings(List<Property> active, List<Property> paused, List<Property> expired);

    void setErrorLayout();

    void setEmptyLayout();
}
