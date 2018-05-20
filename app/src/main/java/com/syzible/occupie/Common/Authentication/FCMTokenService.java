package com.syzible.occupie.Common.Authentication;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class FCMTokenService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        updateServerToken(refreshedToken);
    }

    private void updateServerToken(String token) {
        if (LocalPrefs.isSomeoneLoggedIn(getApplicationContext())) {
            String currentProfile = LocalPrefs.getCurrentProfile(getApplicationContext());
            String uuid = LocalPrefs.getStringPref(getApplicationContext(),
                    currentProfile.equals("Landlord") ? LocalPrefs.Pref.landlord_id : LocalPrefs.Pref.user_id);
            String url = String.format("%s/%s",
                    currentProfile.equals("Landlord") ? Endpoints.LANDLORD : Endpoints.USER,
                    uuid);

            RestClient.get(getApplicationContext(), url, new BaseJsonHttpResponseHandler<JSONObject>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                    try {
                        response.getJSONObject("meta").put("firebase_token", token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        RestClient.put(getApplicationContext(), url, response, null);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {

                }

                @Override
                protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    return new JSONObject(rawJsonData);
                }
            });
        }
    }
}
