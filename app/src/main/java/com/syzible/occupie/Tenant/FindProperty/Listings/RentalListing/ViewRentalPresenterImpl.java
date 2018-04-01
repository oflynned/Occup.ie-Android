package com.syzible.occupie.FindProperty.Listings.RentalListing;

public class ViewRentalPresenterImpl implements ViewRentalPresenter {

    private ViewRentalView viewRentalView;

    @Override
    public void attach(ViewRentalView viewRentalView) {
        this.viewRentalView = viewRentalView;
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
}
