package com.syzible.occupie.Tenant.Applications;

import com.syzible.occupie.Common.Mvp;

public interface ApplicationsPresenter extends Mvp.IPresenter<ApplicationsView> {
    void getApplications();
}
