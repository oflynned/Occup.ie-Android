package com.syzible.occupie.Tenant.FindProperty.Results.HouseShareResults;

import android.content.Context;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class FindHouseShareInteractorImpl implements FindHouseShareInteractor {
    @Override
    public void fetchResult(Context context, final OnFetchCompleted<JSONObject> onFetchCompleted, String id) {
        RestClient.get(context, Endpoints.HOUSE_SHARE + "/" + id, new BaseJsonHttpResponseHandler<JSONObject>() {
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
    public void fetchResults(Context context, HashMap<String, String> query, final OnFetchCompleted<JSONArray> onFetchCompleted) {
        String endpoint = getUrl(query);
        RestClient.get(context, endpoint, new BaseJsonHttpResponseHandler<JSONArray>() {
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

    private String getUrl(HashMap<String, String> query) {
        StringBuilder endpoint = new StringBuilder(String.format("%s/filter?status=active", Endpoints.HOUSE_SHARE));

        for (Map.Entry<String, String> param : query.entrySet()) {
            String key = param.getKey();
            String value = param.getValue();
            endpoint.append(String.format("&%s=%s", key, value));
        }

        return endpoint.toString();
    }
}
