package com.syzible.occupie.FindProperty.Results;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface FindPropertyInteractor {
    void fetchResult(Context context, OnFetchCompleted<JSONObject> onFetchCompleted, String id);

    void fetchResults(Context context, OnFetchCompleted<JSONArray> onFetchCompleted);

    interface OnFetchCompleted<T> {
        void onClientError(String message);

        void onFailure(int statusCode, String message);

        void onSuccess(T results) throws JSONException;
    }
}
