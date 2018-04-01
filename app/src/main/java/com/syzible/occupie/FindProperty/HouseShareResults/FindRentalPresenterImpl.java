package com.syzible.occupie.FindProperty.HouseShareResults.RentalResults;

import android.widget.Toast;

import com.syzible.occupie.Common.Objects.Rental;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FindRentalPresenterImpl implements FindRentalPresenter {
    private FindRentalView findRentalView;
    private FindRentalInteractor findRentalInteractor;

    @Override
    public void attach(FindRentalView findRentalView) {
        this.findRentalView = findRentalView;
        this.findRentalInteractor = new FindRentalInteractorImpl();
    }

    @Override
    public void detach() {
        this.findRentalView = null;
        this.findRentalInteractor = null;
    }

    @Override
    public FindRentalView getNonNullableView() throws IllegalStateException {
        if (findRentalView == null)
            throw new IllegalStateException("view not attached");

        return findRentalView;
    }

    @Override
    public void getProperties() {
        findRentalInteractor.fetchResults(getNonNullableView().getContext(),
                new FindRentalInteractor.OnFetchCompleted<JSONArray>() {
                    @Override
                    public void onFailure(int statusCode, String message) {
                        Toast.makeText(getNonNullableView().getContext(),
                                String.format("%s: %s", statusCode, message), Toast.LENGTH_LONG).show();
                        getNonNullableView().setProgressBarLoading(false);
                    }

                    @Override
                    public void onSuccess(JSONArray results) throws JSONException {
                        try {
                            List<Rental> properties = getProperties(results);
                            getNonNullableView().showProperties(properties);
                            getNonNullableView().setProgressBarLoading(false);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }
                }, findRentalView.getPropertyType());
    }

    private List<Rental> getProperties(JSONArray array) throws JSONException {
        ArrayList<Rental> properties = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                Rental property = new Rental(array.getJSONObject(i));
                properties.add(property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
