package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.syzible.occupie.Common.Mvp;

public interface ViewHouseSharePresenter extends Mvp.IPresenter<ViewHouseShareView> {
    void displayListingDetails();

    CircleOptions getUserCircle(LatLng position);

    int getFillColour(boolean isClear);
}
