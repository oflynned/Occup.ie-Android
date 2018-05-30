package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import com.syzible.occupie.Common.Mvp;

public interface ViewHouseShareView extends Mvp.IView {
    void onEnquireClick();

    void redirectAccountCreation();

    void setListingDetails();
}
