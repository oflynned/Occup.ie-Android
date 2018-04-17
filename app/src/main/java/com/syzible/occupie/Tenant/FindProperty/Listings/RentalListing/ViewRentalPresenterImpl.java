package com.syzible.occupie.Tenant.FindProperty.Listings.RentalListing;

import android.support.design.widget.Snackbar;

import com.syzible.occupie.Common.Objects.Application;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ViewRentalPresenterImpl implements ViewRentalPresenter, ApplicationInteractor.OnApplyCompleted<JSONObject> {

    private ViewRentalView viewRentalView;
    private ApplicationInteractor interactor;

    @Override
    public void attach(ViewRentalView viewRentalView) {
        this.viewRentalView = viewRentalView;
        this.interactor = new ApplicationInteractorImpl();
    }

    @Override
    public ViewRentalView getNonNullableView() throws IllegalStateException {
        if (viewRentalView == null)
            throw new IllegalStateException("view not attached");

        return viewRentalView;
    }

    @Override
    public void detach() {
        this.viewRentalView = null;
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
