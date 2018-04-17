package com.syzible.occupie.Tenant.Applications;

import com.syzible.occupie.Common.Objects.Application;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsPresenterImpl implements ApplicationsPresenter, ApplicationInteractor.OnApplicationInteracted<JSONArray> {

    private ApplicationsView applicationsView;
    private ApplicationInteractor interactor;

    @Override
    public void attach(ApplicationsView applicationView) {
        this.applicationsView = applicationView;
        this.interactor = new ApplicationInteractorImpl();
    }

    @Override
    public ApplicationsView getNonNullableView() throws IllegalStateException {
        if (applicationsView == null)
            throw new IllegalStateException("view not attached");

        return applicationsView;
    }

    @Override
    public void detach() {
        this.applicationsView = null;
    }

    @Override
    public void getApplications() {
        interactor.getApplications(getNonNullableView().getContext(), this);
    }

    @Override
    public void onSuccess(JSONArray results) throws JSONException {
        List<Application> applications = new ArrayList<>();
        for (int i = 0; i < results.length(); i++)
            applications.add(new Application(results.getJSONObject(i)));

        getNonNullableView().showApplications(applications);
    }

    @Override
    public void onFailure(int statusCode, String message) {
        getNonNullableView().showError(statusCode, message);
    }
}
