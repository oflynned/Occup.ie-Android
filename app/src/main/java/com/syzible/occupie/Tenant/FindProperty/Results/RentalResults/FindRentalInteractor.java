package com.syzible.occupie.FindProperty.Results.RentalResults;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface FindRentalInteractor {
    void fetchResult(Context context, OnFetchCompleted<JSONObject> onFetchCompleted, String id);

    void fetchResults(Context context, OnFetchCompleted<JSONArray> onFetchCompleted);

    interface OnFetchCompleted<T> {
        void onFailure(int statusCode, String message);

        void onSuccess(T results) throws JSONException;
    }
}
