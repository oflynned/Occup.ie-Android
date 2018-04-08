package com.syzible.occupie.Tenant.CreateUserAccount.UserOAuthLogin;

import android.content.Context;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
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
    public void onFacebookCallback(CallbackManager callbackManager) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String facebookAccessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        (o, response) -> {
                            try {
                                generatePayload(o, facebookAccessToken);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,gender,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void generatePayload(JSONObject o, String facebookAccessToken) throws JSONException, UnsupportedEncodingException {
        String birthday = DateHelpers.getIso8601Date(new Date(94, 6, 11));

        String facebookId = o.getString("id");
        String pic = "https://graph.facebook.com/" + facebookId + "/picture?type=large";
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
        oauth.put("oauth_id", facebookId);

        JSONObject payload = new JSONObject();
        payload.put("details", details);
        payload.put("meta", meta);
        payload.put("oauth", oauth);

        Context context = getNonNullableView().getContext();
        cacheIdentity(context, facebookId, facebookAccessToken);
        requestAccount(context, payload);
    }

    private void cacheIdentity(Context context, String facebookId, String facebookAccessToken) {
        OAuthUtils.saveId(facebookId, Target.user, context);
        OAuthUtils.saveToken(facebookAccessToken, Target.user, context);
        OAuthUtils.saveProvider("facebook", Target.user, context);
        LocalPrefs.setStringPref(context, LocalPrefs.Pref.current_account, Target.user.name());
    }

    private void requestAccount(Context context, JSONObject payload) throws UnsupportedEncodingException {
        RestClient.get(context, Endpoints.CHECK_USER_EXISTS, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                getNonNullableView().onContinueWithAccount();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                getNonNullableView().onContinueAccountCreation(payload);
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONObject(rawJsonData);
            }
        });
    }
}
