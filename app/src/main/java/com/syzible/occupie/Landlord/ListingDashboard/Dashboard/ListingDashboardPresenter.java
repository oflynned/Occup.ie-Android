package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Mvp;

public interface ListingDashboardPresenter extends Mvp.IPresenter<ListingDashboardView> {
    CallbackParameter onRentalCallback();
}
