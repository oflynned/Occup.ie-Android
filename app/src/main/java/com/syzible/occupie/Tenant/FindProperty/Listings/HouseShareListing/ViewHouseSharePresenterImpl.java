package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import android.graphics.Color;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class ViewHouseSharePresenterImpl implements ViewHouseSharePresenter {

    private ViewHouseShareView viewHouseShareView;
    private static final int RADIUS = 350;

    @Override
    public void attach(ViewHouseShareView viewHouseShareView) {
        this.viewHouseShareView = viewHouseShareView;
    }

    @Override
    public ViewHouseShareView getNonNullableView() throws IllegalStateException {
        if (viewHouseShareView == null)
            throw new IllegalStateException("view not attached");

        return viewHouseShareView;
    }

    @Override
    public void detach() {
        this.viewHouseShareView = null;
    }


    @Override
    public void displayListingDetails() {
        getNonNullableView().setListingDetails();
    }

    @Override
    public CircleOptions getUserCircle(LatLng position) {
        return new CircleOptions()
                .center(position)
                .radius(RADIUS)
                .strokeColor(getFillColour(true))
                .fillColor(getFillColour(false));
    }

    @Override
    public int getFillColour(boolean isClear) {
        final int GREEN_500 = Integer.parseInt("4CAF50", 16);
        int r = (GREEN_500) & 0xFF;
        int g = (GREEN_500 >> 8) & 0xFF;
        int b = (GREEN_500 >> 16) & 0xFF;
        int a = isClear ? 0 : 64;

        return Color.argb(a, r, g, b);
    }
}
