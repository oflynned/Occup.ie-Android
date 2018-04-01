package com.syzible.occupie.FindProperty.HouseShareResults.RentalResults;

import android.content.Context;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class FindRentalInteractorImpl implements FindRentalInteractor {
    @Override
    public void fetchResult(Context context, final OnFetchCompleted<JSONObject> onFetchCompleted, FindRentalFragment.PropertyType propertyType, String id) {
        RestClient.get(context, (propertyType == FindRentalFragment.PropertyType.rent ? Endpoints.RENTAL : Endpoints.HOUSE_SHARE) + "/" + id, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                try {
                    onFetchCompleted.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                onFetchCompleted.onFailure(statusCode, rawJsonData);
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONObject(rawJsonData);
            }
        });
    }

    @Override
    public void fetchResults(Context context, final OnFetchCompleted<JSONArray> onFetchCompleted, FindRentalFragment.PropertyType propertyType) {
        RestClient.get(context, (propertyType == FindRentalFragment.PropertyType.rent ? Endpoints.RENTAL : Endpoints.HOUSE_SHARE), new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                try {
                    onFetchCompleted.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONArray errorResponse) {
                onFetchCompleted.onFailure(statusCode, rawJsonData);
            }

            @Override
            protected JSONArray parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONArray(rawJsonData);
            }
        });
    }
}
