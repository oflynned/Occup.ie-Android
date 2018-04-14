package com.syzible.occupie.Tenant.CreateUserAccount.UserProfileBuilder;

import com.syzible.occupie.Common.Mvp;

import org.json.JSONArray;
import org.json.JSONException;

public interface UserProfileBuilderView extends Mvp.IView {
    void onProfileCompleted();

    String getDescription();

    JSONArray getHobbies() throws JSONException;
}
