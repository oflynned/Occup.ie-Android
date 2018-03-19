package com.syzible.rentapp.FindProperty.Listing;

public class ViewListingPresenterImpl implements ViewListingPresenter {

    private ViewListingView viewListingView;

    @Override
    public void attach(ViewListingView viewListingView) {
        this.viewListingView = viewListingView;
    }

    @Override
    public ViewListingView getNonNullableView() throws IllegalStateException {
        if (viewListingView == null)
            throw new IllegalStateException("view not attached");

        return viewListingView;
    }

    @Override
    public void detach() {
        this.viewListingView = null;
    }
}
