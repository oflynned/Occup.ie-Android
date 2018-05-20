package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin;

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
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;
import com.syzible.occupie.Common.Persistence.OAuthUtils;
import com.syzible.occupie.Common.Persistence.Target;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LandlordOAuthLoginInteractorImpl implements LandlordOAuthLoginInteractor {
    private LandlordOAuthLoginView view;

    LandlordOAuthLoginInteractorImpl(LandlordOAuthLoginView view) {
        this.view = view;
    }

    private LandlordOAuthLoginView getView() {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public void cacheOAuthIdentity(Context context, String provider, String oauthId, String accessToken, String forename, String surname) {
        OAuthUtils.saveId(oauthId, Target.landlord, context);
        OAuthUtils.saveToken(accessToken, Target.landlord, context);
        OAuthUtils.saveProvider(provider, Target.landlord, context);
        LocalPrefs.setStringPref(context, LocalPrefs.Pref.landlord_forename, forename);
        LocalPrefs.setStringPref(context, LocalPrefs.Pref.landlord_surname, surname);
        LocalPrefs.setStringPref(context, LocalPrefs.Pref.current_account, Target.landlord.name());
    }

    @Override
    public void requestAccount(Context context, JSONObject payload) {
        RestClient.get(context, Endpoints.CHECK_LANDLORD_EXISTS, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                try {
                    LocalPrefs.setStringPref(context, LocalPrefs.Pref.landlord_id, response.getString("_id"));
                    getView().onContinueToMain();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                getView().onContinueAccountCreation(payload);
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONObject(rawJsonData);
            }
        });
    }

    @Override
    public void requestFacebookData(CallbackManager callbackManager, CallbackOAuth callback) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String facebookAccessToken = loginResult.getAccessToken().getToken();
                String facebookUserId = loginResult.getAccessToken().getUserId();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        (o, response) -> callback.onSuccess(facebookUserId, facebookAccessToken, o));

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
                callback.onFailure();
            }
        });
    }
}
