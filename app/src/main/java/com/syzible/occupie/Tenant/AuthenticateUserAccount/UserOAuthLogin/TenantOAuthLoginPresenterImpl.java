package com.syzible.occupie.Tenant.AuthenticateUserAccount.UserOAuthLogin;

import android.content.Context;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Helpers.CallbackOAuth;
import com.syzible.occupie.Common.Helpers.DateHelpers;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;
import com.syzible.occupie.Common.Persistence.OAuthUtils;
import com.syzible.occupie.Common.Persistence.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class TenantOAuthLoginPresenterImpl implements TenantOAuthLoginPresenter {

    private TenantOAuthLoginView view;

    @Override
    public void attach(TenantOAuthLoginView view) {
        this.view = view;
    }

    @Override
    public TenantOAuthLoginView getNonNullableView() throws IllegalStateException {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public CallbackOAuth onFacebookCallback() {
        return new CallbackOAuth() {
            @Override
            public void onSuccess(String userId, String accessToken, JSONObject profile) {
                try {
                    JSONObject payload = generatePayload(userId, profile);
                    String forename = profile.getString("first_name");
                    String surname = profile.getString("last_name");
                    getNonNullableView().cacheOAuthIdentity("facebook", userId, accessToken, forename, surname);
                    getNonNullableView().requestAccount(payload);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure() {

            }
        };
    }

    @Override
    public JSONObject generatePayload(String userId, JSONObject o) throws JSONException, UnsupportedEncodingException {
        String birthday = DateHelpers.getIso8601Date(new Date(94, 6, 11));

        String pic = "https://graph.facebook.com/" + userId + "/picture?type=large";
        String email = o.getString("email");
        String forename = o.getString("first_name");
        String surname = o.getString("last_name");
        String sex = o.getString("gender");

        JSONObject details = new JSONObject();
        details.put("email", email);
        details.put("forename", URLEncoder.encode(forename, "UTF-8"));
        details.put("surname", URLEncoder.encode(surname, "UTF-8"));
        details.put("profile_picture", pic);
        details.put("sex", sex);
        details.put("dob", birthday);
        details.put("profession", "professional");

        JSONObject meta = new JSONObject();
        meta.put("identity_verified", false);
        meta.put("creation_time", System.currentTimeMillis());
        meta.put("firebase_token", "firebase_token");
        meta.put("tos_version_accepted", 1);
        meta.put("privacy_version_accepted", 1);

        JSONObject oauth = new JSONObject();
        oauth.put("oauth_provider", "facebook");
        oauth.put("oauth_id", userId);

        JSONObject payload = new JSONObject();
        payload.put("details", details);
        payload.put("meta", meta);
        payload.put("oauth", oauth);

        return payload;
    }
}
