package com.syzible.occupie.Tenant.FindProperty.Listings.RentalListing;

import com.syzible.occupie.Common.Mvp;

public interface ViewRentalView extends Mvp.IView {
    void onEnquireClick();

    void redirectAccountCreation();

    void setListingDetails();
}
