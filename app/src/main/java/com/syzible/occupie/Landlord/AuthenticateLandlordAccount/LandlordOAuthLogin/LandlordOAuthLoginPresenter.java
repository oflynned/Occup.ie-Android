package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin;

import com.syzible.occupie.Common.Helpers.Callback;
import com.syzible.occupie.Common.Helpers.CallbackOAuth;
import com.syzible.occupie.Common.Helpers.CallbackParameter;
import com.syzible.occupie.Common.Mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface LandlordOAuthLoginPresenter extends Mvp.IPresenter<LandlordOAuthLoginView> {
    void attach(LandlordOAuthLoginView landlordOAuthLoginView);

    CallbackOAuth onFacebookCallback();

    JSONObject generatePayload(String userId, JSONObject o) throws JSONException, UnsupportedEncodingException;
}
