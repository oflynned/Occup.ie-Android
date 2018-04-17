package com.syzible.occupie.Tenant.FindProperty.Listings.RentalListing;

import android.content.Context;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Objects.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class ApplicationInteractorImpl implements ApplicationInteractor {
    @Override
    public void apply(Context context, OnApplyCompleted<JSONObject> onApplyCompleted, Application application) throws UnsupportedEncodingException, JSONException {
        RestClient.post(context, Endpoints.APPLICATION, application.getPayload(), new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                try {
                    onApplyCompleted.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                onApplyCompleted.onFailure(statusCode, rawJsonData);
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                System.out.println(rawJsonData);
                return new JSONObject(rawJsonData);
            }
        });
    }
}
