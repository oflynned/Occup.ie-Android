package com.syzible.occupie.Tenant.AuthenticateUserAccount.UserProfileBuilder;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface UserProfileBuilderPresenter extends Mvp.IPresenter<UserProfileBuilderView> {
    void createAccount(JSONObject account) throws UnsupportedEncodingException, JSONException;
}
