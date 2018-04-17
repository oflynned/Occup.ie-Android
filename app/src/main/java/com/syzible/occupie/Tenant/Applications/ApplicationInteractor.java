package com.syzible.occupie.Tenant.Applications;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

public interface ApplicationInteractor {

    void getApplications(Context context, OnApplicationInteracted<JSONArray> callback);

    interface OnApplicationInteracted<T> {
        void onSuccess(T result) throws JSONException;

        void onFailure(int statusCode, String message);
    }
}
