package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin;

import android.content.Context;

import com.facebook.CallbackManager;
import com.syzible.occupie.Common.Helpers.CallbackOAuth;
import com.syzible.occupie.Common.Helpers.CallbackOption;
import com.syzible.occupie.Common.Helpers.CallbackParameter;

import org.json.JSONObject;

public interface LandlordOAuthLoginInteractor {
    void cacheOAuthIdentity(Context context, String provider, String oauthId, String accessToken, String forename, String surname);

    void requestAccount(Context context, JSONObject payload);

    void requestFacebookData(CallbackManager callbackManager, CallbackOAuth callback);
}
