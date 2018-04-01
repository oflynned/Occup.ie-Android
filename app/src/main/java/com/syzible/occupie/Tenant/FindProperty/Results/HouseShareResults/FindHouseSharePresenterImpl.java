package com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults;

import android.widget.Toast;

import com.syzible.occupie.Common.Objects.HouseShare;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FindHouseSharePresenterImpl implements FindHouseSharePresenter {
    private FindHouseShareView findHouseShareView;
    private FindHouseShareInteractor findHouseShareInteractor;

    @Override
    public void attach(FindHouseShareView findHouseShareView) {
        this.findHouseShareView = findHouseShareView;
        this.findHouseShareInteractor = new FindHouseShareInteractorImpl();
    }

    @Override
    public void detach() {
        this.findHouseShareView = null;
        this.findHouseShareInteractor = null;
    }

    @Override
    public FindHouseShareView getNonNullableView() throws IllegalStateException {
        if (findHouseShareView == null)
            throw new IllegalStateException("view not attached");

        return findHouseShareView;
    }

    @Override
    public void getProperties() {
        findHouseShareInteractor.fetchResults(getNonNullableView().getContext(),
                new FindHouseShareInteractor.OnFetchCompleted<JSONArray>() {
                    @Override
                    public void onFailure(int statusCode, String message) {
                        Toast.makeText(getNonNullableView().getContext(),
                                String.format("%s: %s", statusCode, message), Toast.LENGTH_LONG).show();
                        getNonNullableView().setProgressBarLoading(false);
                    }

                    @Override
                    public void onSuccess(JSONArray results) throws JSONException {
                        try {
                            List<HouseShare> properties = getProperties(results);
                            getNonNullableView().showProperties(properties);
                            getNonNullableView().setProgressBarLoading(false);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private List<HouseShare> getProperties(JSONArray array) throws JSONException {
        ArrayList<HouseShare> properties = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                HouseShare property = new HouseShare(array.getJSONObject(i));
                properties.add(property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
