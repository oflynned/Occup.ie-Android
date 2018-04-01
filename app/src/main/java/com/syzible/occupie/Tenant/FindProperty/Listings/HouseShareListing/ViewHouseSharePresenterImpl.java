package com.syzible.occupie.FindProperty.Listings.HouseShareListing;

public class ViewHouseSharePresenterImpl implements ViewHouseSharePresenter {

    private ViewHouseShareView viewHouseShareView;

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
}
