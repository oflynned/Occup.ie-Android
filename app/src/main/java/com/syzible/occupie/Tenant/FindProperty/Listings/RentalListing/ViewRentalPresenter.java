package com.syzible.occupie.Tenant.FindProperty.Listings.RentalListing;

import com.syzible.occupie.Common.Mvp;
import com.syzible.occupie.Common.Objects.Application;

public interface ViewRentalPresenter extends Mvp.IPresenter<ViewRentalView> {
    void displayListingDetails();

    void applyToListing(Application application);
}
