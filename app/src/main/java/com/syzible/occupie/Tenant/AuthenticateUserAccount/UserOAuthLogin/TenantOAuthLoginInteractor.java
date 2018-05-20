package com.syzible.occupie.Tenant.AuthenticateUserAccount.UserOAuthLogin;

import android.content.Context;

import com.facebook.CallbackManager;
import com.syzible.occupie.Common.Helpers.CallbackOAuth;

import org.json.JSONObject;

public interface TenantOAuthLoginInteractor {
    void cacheOAuthIdentity(Context context, String provider, String oauthId, String accessToken, String forename, String surname);

    void requestAccount(Context context, JSONObject payload);

    void requestFacebookData(CallbackManager callbackManager, CallbackOAuth callback);
}
