package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin;

import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface LandlordOAuthLoginPresenter extends Mvp.IPresenter<LandlordOAuthLoginView> {
    void attach(LandlordOAuthLoginView landlordOAuthLoginView);

    CallbackParameter<JSONObject> onFacebookCallback(String userId, String accessToken);

    JSONObject generatePayload(JSONObject o, String facebookAccessToken) throws JSONException, UnsupportedEncodingException;
}
