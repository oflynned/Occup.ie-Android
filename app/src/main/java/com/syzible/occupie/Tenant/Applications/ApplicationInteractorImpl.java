package com.syzible.occupie.Tenant.Applications;

import android.content.Context;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class ApplicationInteractorImpl implements ApplicationInteractor {

    @Override
    public void getApplications(Context context, OnApplicationInteracted<JSONArray> callback) {
        String uuid = LocalPrefs.getStringPref(context, LocalPrefs.Pref.user_id);
        String endpoint = String.format("%s?user_id=%s", Endpoints.APPLICATION, uuid);
        RestClient.get(context, endpoint, new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                try {
                    callback.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onFailure(422, e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONArray errorResponse) {
                callback.onFailure(statusCode, rawJsonData);
            }

            @Override
            protected JSONArray parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONArray(rawJsonData);
            }
        });
    }
}
