package com.syzible.occupie.Tenant.AuthenticateUserAccount.UserOAuthLogin;

import com.facebook.CallbackManager;
import com.syzible.occupie.Common.Helpers.CallbackOAuth;
import com.syzible.occupie.Common.Mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface TenantOAuthLoginPresenter extends Mvp.IPresenter<TenantOAuthLoginView> {
    void attach(TenantOAuthLoginView tenantOAuthLoginView);

    CallbackOAuth onFacebookCallback();

    JSONObject generatePayload(String userId, JSONObject o) throws JSONException, UnsupportedEncodingException;
}
