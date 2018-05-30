package com.syzible.occupie.Tenant.Applications;

import com.syzible.occupie.Common.Mvp;
import com.syzible.occupie.Common.Objects.Application;

import org.json.JSONArray;

import java.util.List;

public interface ApplicationsView extends Mvp.IView {
    void hideProgressBar();

    void showApplications(List<Application> results);

    void showEmpty();

    void showError(int statusCode, String message);
}
