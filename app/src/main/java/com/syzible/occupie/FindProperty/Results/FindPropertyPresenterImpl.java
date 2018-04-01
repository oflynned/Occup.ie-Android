package com.syzible.occupie.FindProperty.Results;

import android.util.Log;
import android.widget.Toast;

import com.syzible.occupie.Common.Objects.Property;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FindPropertyPresenterImpl implements FindPropertyPresenter {
    private FindPropertyView findPropertyView;
    private FindPropertyInteractor findPropertyInteractor;

    @Override
    public void attach(FindPropertyView findPropertyView) {
        this.findPropertyView = findPropertyView;
        this.findPropertyInteractor = new FindPropertyInteractorImpl();
    }

    @Override
    public void detach() {
        this.findPropertyView = null;
        this.findPropertyInteractor = null;
    }

    @Override
    public FindPropertyView getNonNullableView() throws IllegalStateException {
        if (findPropertyView == null)
            throw new IllegalStateException("view not attached");

        return findPropertyView;
    }

    @Override
    public void getProperties() {
        findPropertyInteractor.fetchResults(getNonNullableView().getContext(),
                new FindPropertyInteractor.OnFetchCompleted<JSONArray>() {
                    @Override
                    public void onClientError(String message) {
                        Log.e(getClass().getSimpleName(), message);
                    }

                    @Override
                    public void onFailure(int statusCode, String message) {
                        Toast.makeText(getNonNullableView().getContext(),
                                String.format("%s: %s", statusCode, message), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(JSONArray results) throws JSONException {
                        try {
                            List<Property> properties = getProperties(results);
                            getNonNullableView().showProperties(properties);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private List<Property> getProperties(JSONArray array) throws JSONException {
        ArrayList<Property> properties = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                Property property = new Property(array.getJSONObject(i));
                properties.add(property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
