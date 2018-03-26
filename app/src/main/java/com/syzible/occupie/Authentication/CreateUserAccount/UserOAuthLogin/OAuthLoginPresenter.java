package com.syzible.occupie.Authentication.CreateUserAccount.UserOAuthLogin;

import com.facebook.CallbackManager;
import com.syzible.occupie.Common.Mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface OAuthLoginPresenter extends Mvp.IPresenter<OAuthLoginView> {
    void onFacebookCallback(CallbackManager callbackManager);

    void generatePayload(JSONObject o, String facebookAccessToken) throws JSONException, UnsupportedEncodingException;
}
