package com.syzible.occupie.Tenant.FindProperty.Listings.HouseShareListing;

import android.graphics.Color;
import android.support.design.widget.Snackbar;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.syzible.occupie.Common.Objects.Application;
import com.syzible.occupie.Tenant.FindProperty.Common.ApplicationInteractor;
import com.syzible.occupie.Tenant.FindProperty.Common.ApplicationInteractorImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ViewHouseSharePresenterImpl implements ViewHouseSharePresenter, ApplicationInteractor.OnApplyCompleted<JSONObject> {

    private ViewHouseShareView viewHouseShareView;
    private ApplicationInteractor interactor;
    private static final int RADIUS = 350;

    @Override
    public void attach(ViewHouseShareView viewHouseShareView) {
        this.viewHouseShareView = viewHouseShareView;
        this.interactor = new ApplicationInteractorImpl();
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
    public void applyToListing(Application application) {
        try {
            interactor.apply(getNonNullableView().getContext(), this, application);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onFailure(int statusCode, String message) {
        switch (statusCode) {
            case 403:
                Snackbar.make(getNonNullableView().getView(), "Sorry! You're not a suitable candidate for this property.", Snackbar.LENGTH_LONG).show();
                break;
            case 500:
                Snackbar.make(getNonNullableView().getView(), "Sorry! You've already applied to this property.", Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(getNonNullableView().getView(), "Sorry! There was a problem in applying for this property.", Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onSuccess(JSONObject result) {
        Snackbar.make(getNonNullableView().getView(), "Application successful!", Snackbar.LENGTH_LONG).show();
    }
}
