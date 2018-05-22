package com.syzible.occupie.Landlord.ListingDashboard;

public class ListingDashboardPresenterImpl implements ListingDashboardPresenter {

    private ListingDashboardView view;

    @Override
    public void attach(ListingDashboardView listingDashboardView) {
        this.view = listingDashboardView;
    }

    @Override
    public ListingDashboardView getNonNullableView() throws IllegalStateException {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
