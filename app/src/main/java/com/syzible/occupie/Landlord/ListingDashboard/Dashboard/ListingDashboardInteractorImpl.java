package com.syzible.occupie.Landlord.ListingDashboard.Dashboard;

import android.content.Context;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Network.RestClient;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class ListingDashboardInteractorImpl implements ListingDashboardInteractor {
    @Override
    public void fetchRentals(Context context, String endpoint, CallbackParameter<JSONArray>callback) {
        RestClient.get(context, endpoint, new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONArray errorResponse) {
                callback.onFailure();
            }

            @Override
            protected JSONArray parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONArray(rawJsonData);
            }
        });
    }
}
