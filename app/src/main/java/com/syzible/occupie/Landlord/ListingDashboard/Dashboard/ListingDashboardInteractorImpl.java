package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.content.Context;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Network.RestClient;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ListingDashboardInteractorImpl implements ListingDashboardInteractor {
    @Override
    public void fetchListings(Context context, String endpoint, CallbackParameter<JSONObject> callback) {
        RestClient.get(context, endpoint, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                try {
                    callback.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                callback.onFailure();
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONObject(rawJsonData);
            }
        });
    }
}
