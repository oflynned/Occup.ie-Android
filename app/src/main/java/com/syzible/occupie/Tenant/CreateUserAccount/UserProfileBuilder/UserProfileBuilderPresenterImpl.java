package com.syzible.occupie.Tenant.CreateUserAccount.UserProfileBuilder;

import android.support.design.widget.Snackbar;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.occupie.Common.Network.Endpoints;
import com.syzible.occupie.Common.Network.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class UserProfileBuilderPresenterImpl implements UserProfileBuilderPresenter {
    private UserProfileBuilderView view;

    @Override
    public void attach(UserProfileBuilderView view) {
        this.view = view;
    }

    @Override
    public UserProfileBuilderView getNonNullableView() throws IllegalStateException {
        if (view == null)
            throw new IllegalStateException();

        return view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void createAccount(JSONObject account) throws UnsupportedEncodingException, JSONException {
        JSONObject details = account.getJSONObject("details");
        details.put("hobbies", getNonNullableView().getHobbies()); // TODO fetch and parse real hobby data
        details.put("description", getNonNullableView().getDescription()); // TODO fetch real description
        account.put("details", details);

        RestClient.post(getNonNullableView().getContext(), Endpoints.USER, account, new BaseJsonHttpResponseHandler<JSONObject>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
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
