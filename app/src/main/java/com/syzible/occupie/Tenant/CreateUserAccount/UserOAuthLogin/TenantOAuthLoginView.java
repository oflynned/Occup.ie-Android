package com.syzible.occupie.Tenant.CreateUserAccount.UserOAuthLogin;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONObject;

public interface TenantOAuthLoginView extends Mvp.IView {
    void onContinueToMain();

    void onTosClick();

    void onContinueAccountCreation(JSONObject oauthProfile);
}
