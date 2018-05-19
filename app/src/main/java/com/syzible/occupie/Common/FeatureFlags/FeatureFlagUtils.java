package com.syzible.occupie.Common.FeatureFlags;

import android.content.Context;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FeatureFlagUtils {

    private static ParameterCallback<List<FeatureFlag>> getSaveLocallyCallback(Context context, Callback callback) {
        return new ParameterCallback<List<FeatureFlag>>() {
            @Override
            public void onSuccess(List<FeatureFlag> featureFlags) {
                FeatureFlagDatabaseHelper.updateFeatureFlags(context, featureFlags);
                callback.onCallback();
            }

            @Override
            public void onFailure() {

            }
        };
    }

    public static FeatureFlag getFeatureFlag(Context context, Flags flag) {
        try {
            return FeatureFlagDatabaseHelper.getFeatureFlag(context, flag);
        } catch (FeatureFlagNotPresentException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isFlagEnabled(Context context, Flags flagName) {
        try {
            FeatureFlag featureFlag = FeatureFlagDatabaseHelper.getFeatureFlag(context, flagName);
            return featureFlag.isEnabled();
        } catch (FeatureFlagNotPresentException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean shouldFlagShowDialog(Context context, Flags flagName) {
        try {
            FeatureFlag featureFlag = FeatureFlagDatabaseHelper.getFeatureFlag(context, flagName);
            return featureFlag.shouldShowDialog();
        } catch (FeatureFlagNotPresentException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static boolean shouldFlagKickSession(Context context, Flags flagName) {
        try {
            FeatureFlag featureFlag = FeatureFlagDatabaseHelper.getFeatureFlag(context, flagName);
            return featureFlag.shouldKickSession();
        } catch (FeatureFlagNotPresentException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void getRemoteFeatureFlags(Context context, Callback action) {
        ParameterCallback<List<FeatureFlag>> callback = getSaveLocallyCallback(context, action);

        RestClient.get(context, Endpoints.FEATURE_FLAGS, new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                List<FeatureFlag> featureFlags = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject o = response.getJSONObject(i);
                        String flagName = o.keys().next();
                        JSONObject attributes = o.getJSONObject(flagName);
                        FeatureFlag featureFlag = new FeatureFlag(flagName, attributes);
                        featureFlags.add(featureFlag);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccess(featureFlags);
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
