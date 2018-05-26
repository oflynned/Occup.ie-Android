package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordDetailsConfirmation;

import android.content.Context;
import android.support.design.widget.Snackbar;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;
import com.syzible.occupie.Common.Persistence.LocalPrefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class LandlordDetailsConfirmationInteractorImpl implements LandlordDetailsConfirmationInteractor {
    private LandlordDetailsConfirmationView view;

    LandlordDetailsConfirmationInteractorImpl(LandlordDetailsConfirmationView view) {
        this.view = view;
    }

    private LandlordDetailsConfirmationView getNonNullableView() {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public void createAccount(Context context, JSONObject profile) throws UnsupportedEncodingException {
        RestClient.post(context, Endpoints.LANDLORD, profile, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                try {
                    String uuid = response.getString("_id");
                    LocalPrefs.setStringPref(context, LocalPrefs.Pref.landlord_id, uuid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getNonNullableView().onProfileCompleted();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                Snackbar.make(getNonNullableView().getView(), String.format("%s: %s", statusCode, rawJsonData), Snackbar.LENGTH_LONG).show();
            }

            @Override
            protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return new JSONObject(rawJsonData);
            }
        });
    }
}
