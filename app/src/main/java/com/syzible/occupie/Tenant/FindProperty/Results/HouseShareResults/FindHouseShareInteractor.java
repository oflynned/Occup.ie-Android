package com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public interface FindHouseShareInteractor {
    void fetchResult(Context context, OnFetchCompleted<JSONObject> onFetchCompleted, String id);

    void fetchResults(Context context, HashMap<String, String> query, OnFetchCompleted<JSONArray> onFetchCompleted);

    interface OnFetchCompleted<T> {
        void onFailure(int statusCode, String message);

        void onSuccess(T results) throws JSONException;
    }
}
