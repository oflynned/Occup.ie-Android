package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.syzible.occupie.Common.Mvp;
import com.syzible.occupie.Common.Objects.Application;

public interface ViewHouseSharePresenter extends Mvp.IPresenter<ViewHouseShareView> {
    CircleOptions getUserCircle(LatLng position);

    int getFillColour(boolean isClear);

    void displayListingDetails();

    void applyToListing(Application application);
}
