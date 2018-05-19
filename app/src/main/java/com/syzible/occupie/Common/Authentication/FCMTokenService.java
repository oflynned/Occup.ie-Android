package com.syzible.occupie.Common.Authentication;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class FCMTokenService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        updateServerToken(refreshedToken);
    }

    private void updateServerToken(String token) {
        System.out.println(token);
        if (LocalPrefs.isLandlordLoggedIn(getApplicationContext()) || LocalPrefs.isUserLoggedIn(getApplicationContext())) {
            JSONObject payload = new JSONObject();
            JSONObject meta = new JSONObject();
            try {
                meta.put("firebase_token", token);
                payload.put("meta", meta);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String endpoint = LocalPrefs.getCurrentProfile(getApplicationContext())
                    .equals("Landlord") ? Endpoints.LANDLORD : Endpoints.USER;
            try {
                RestClient.put(getApplicationContext(), endpoint, payload, null);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
