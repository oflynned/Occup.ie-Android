package com.syzible.occupie.Tenant.CreateUserAccount.UserOAuthLogin;

import com.facebook.CallbackManager;
import com.syzible.occupie.Common.Mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface TenantOAuthLoginPresenter extends Mvp.IPresenter<TenantOAuthLoginView> {
    void onFacebookCallback(CallbackManager callbackManager);

    void generatePayload(JSONObject o, String facebookAccessToken) throws JSONException, UnsupportedEncodingException;
}
