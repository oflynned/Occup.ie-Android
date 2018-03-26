package com.syzible.occupie.Authentication.CreateUserAccount.UserOAuthLogin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.syzible.occupie.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FragmentOAuthLogin extends Fragment {

    private CallbackManager callbackManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_oauth_login, container, false);

        LoginButton facebookLoginButton = view.findViewById(R.id.tenant_facebook_login_button);
        facebookLoginButton.setOnClickListener(v -> LoginManager.getInstance().logInWithReadPermissions(
                FragmentOAuthLogin.this,
                Arrays.asList("public_profile", "email")
                // TODO request birthday
        ));

        registerFacebookCallback();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void registerFacebookCallback() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String facebookAccessToken = loginResult.getAccessToken().getToken();
                // FacebookUtils.saveToken(accessToken, AuthenticationActivity.this);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        (o, response) -> {
                            try {
                                System.out.println(o);
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
                                System.out.println(details.toString());

                                JSONObject meta = new JSONObject();
                                meta.put("identity_verified", false);
                                meta.put("creation_time", System.currentTimeMillis());
                                meta.put("firebase_token", "firebase_token");
                                meta.put("tos_version_accepted", 1);
                                meta.put("privacy_version_accepted", 1);
                                System.out.println(meta.toString());

                                JSONObject oauth = new JSONObject();
                                oauth.put("oauth_provider", "facebook");
                                oauth.put("oauth_id", facebookId);
                                oauth.put("oauth_token", facebookAccessToken);
                                System.out.println(oauth.toString());

                                JSONObject payload = new JSONObject();
                                payload.put("details", details);
                                payload.put("meta", meta);
                                payload.put("oauth", oauth);
                                System.out.println(payload);
                            } catch (JSONException e) {
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
}
