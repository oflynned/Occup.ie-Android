package com.syzible.occupie.Common.Helpers;

import org.json.JSONObject;

public interface CallbackOAuth {
    void onSuccess(String userId, String accessToken, JSONObject profile);

    void onFailure();
}
