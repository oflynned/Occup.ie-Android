package com.syzible.occupie.Tenant.FindProperty.Listings.RentalListing;

import android.content.Context;

import com.syzible.occupie.Common.Objects.Application;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface ApplicationInteractor {
    void apply(Context context, OnApplyCompleted<JSONObject> onApplyCompleted, Application application) throws UnsupportedEncodingException, JSONException;

    interface OnApplyCompleted<T> {
        void onFailure(int statusCode, String message);

        void onSuccess(T result) throws JSONException;
    }
}
