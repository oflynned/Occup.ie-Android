package com.syzible.occupie.FindProperty.Results;

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
        findPropertyInteractor.fetchResults(new FindPropertyInteractor.OnFetchCompleted<JSONArray>() {
            @Override
            public void onFailure(int statusCode, String message) {
                Toast.makeText(findPropertyView.getContext(), String.format("%s: %s", statusCode, message), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(JSONArray results) throws JSONException {
                List<Property> properties = getProperties(results);
                try {
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
            Property property = null;
            try {
                property = new Property(array.getJSONObject(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            properties.add(property);
        }

        return properties;
    }
}
