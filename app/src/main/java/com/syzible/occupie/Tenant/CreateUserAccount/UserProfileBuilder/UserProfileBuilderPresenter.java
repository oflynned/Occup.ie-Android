package com.syzible.occupie.Tenant.CreateUserAccount.UserProfileBuilder;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface UserProfileBuilderPresenter extends Mvp.IPresenter<UserProfileBuilderView> {
    void createAccount(JSONObject account) throws UnsupportedEncodingException;
}
