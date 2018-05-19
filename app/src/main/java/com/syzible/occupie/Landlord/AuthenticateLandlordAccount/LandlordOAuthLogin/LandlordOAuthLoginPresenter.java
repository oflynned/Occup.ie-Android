package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin;

import com.facebook.CallbackManager;
import com.syzible.occupie.Common.Mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface LandlordOAuthLoginPresenter extends Mvp.IPresenter<LandlordOAuthLoginView> {
    void onFacebookCallback(CallbackManager callbackManager);

    void generatePayload(JSONObject o, String facebookAccessToken) throws JSONException, UnsupportedEncodingException;
}
