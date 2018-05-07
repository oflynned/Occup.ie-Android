package com.syzible.occupie.Common.Authentication;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.syzible.occupie.Common.Persistence.LocalPrefs;

import org.json.JSONException;
import org.json.JSONObject;

public class FCMTokenService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        updateServerToken(refreshedToken);
    }

    private void updateServerToken(String token) {
        if (LocalPrefs.isLandlordLoggedIn(getApplicationContext()) || LocalPrefs.isUserLoggedIn(getApplicationContext())) {
            JSONObject payload = new JSONObject();
            try {
                payload.put("fcm_token", token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
