package com.syzible.occupie.Tenant.CreateUserAccount.UserDetailsConfirmation;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface UserDetailsConfirmationPresenter extends Mvp.IPresenter<UserDetailsConfirmationView> {
    void parsePayload(JSONObject oauth);

    void updateAccount() throws UnsupportedEncodingException;
}
