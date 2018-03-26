package com.syzible.occupie.Authentication.CreateUserAccount.UserOAuthLogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;
import com.syzible.occupie.Common.Persistence.OAuthUtils;
import com.syzible.occupie.Common.Persistence.Target;
import com.syzible.occupie.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class OAuthLoginPresenterImpl implements OAuthLoginPresenter {

    private OAuthLoginView view;

    @Override
    public void attach(OAuthLoginView view) {
        this.view = view;
    }

    @Override
    public OAuthLoginView getNonNullableView() throws IllegalStateException {
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
        String facebookId = o.getString("id");
        String pic = "https://graph.facebook.com/" + facebookId + "/picture?type=large";
        String email = o.getString("email");
        String forename = o.getString("first_name");
        String surname = o.getString("last_name");
        String sex = o.getString("gender");

        JSONObject details = new JSONObject();
        details.put("email", email);
        details.put("forename", forename);
        details.put("surname", surname);
        details.put("profile_picture", pic);
        details.put("sex", sex);
        details.put("dob", "11/07/1994");
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
        oauth.put("oauth_token", facebookAccessToken);

        JSONObject payload = new JSONObject();
        payload.put("details", details);
        payload.put("meta", meta);
        payload.put("oauth", oauth);

        RestClient.post(getNonNullableView().getContext(), Endpoints.USER, payload, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                Context context = getNonNullableView().getContext();
                try {
                    OAuthUtils.saveId(oauth.getString("oauth_id"), Target.user, context);
                    OAuthUtils.saveToken(oauth.getString("oauth_token"), Target.user, context);
                    OAuthUtils.saveProvider(oauth.getString("oauth_provider"), Target.user, context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LocalPrefs.setStringPref(context, LocalPrefs.Pref.user_forename, forename);
                LocalPrefs.setStringPref(context, LocalPrefs.Pref.user_surname, surname);
                LocalPrefs.setStringPref(context, LocalPrefs.Pref.current_account, Target.user.name());
                LocalPrefs.setBooleanPref(context, LocalPrefs.Pref.is_user_first_run_done, true);

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                Toast.makeText(getNonNullableView().getContext(), String.format("%s: failure!", statusCode), Toast.LENGTH_LONG).show();
                System.out.println(statusCode + " " + errorResponse);
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONObject(rawJsonData);
            }
        });
    }
}
