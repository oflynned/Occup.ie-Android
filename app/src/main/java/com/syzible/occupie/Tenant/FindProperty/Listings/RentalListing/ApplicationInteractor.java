package com.syzible.occupie.Tenant.FindProperty.Listings.RentalListing;

import android.content.Context;

import com.syzible.occupie.Common.Objects.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface FindRentalInteractor {
    void apply(Context context, OnFetchCompleted<JSONObject> onFetchCompleted, Application application);

    interface OnFetchCompleted<T> {
        void onFailure(int statusCode, String message);

        void onSuccess(T result) throws JSONException;
    }
}
