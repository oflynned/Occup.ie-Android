package com.syzible.occupie.FindProperty.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface FindPropertyInteractor {
    void fetchResult(OnFetchCompleted<JSONObject> onFetchCompleted, String id);

    void fetchResults(OnFetchCompleted<JSONArray> onFetchCompleted);

    interface OnFetchCompleted<T> {
        void onFailure(int statusCode, String message);

        void onSuccess(T results) throws JSONException;
    }
}
