package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordOAuthLogin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;

import org.json.JSONObject;

import java.util.Arrays;

import mehdi.sakout.fancybuttons.FancyButton;

public class LandlordOAuthLoginFragment extends Fragment implements LandlordOAuthLoginView {

    private CallbackManager callbackManager;
    private LandlordOAuthLoginPresenter presenter;

    public LandlordOAuthLoginFragment() {

    }

    public static LandlordOAuthLoginFragment getInstance() {
        return new LandlordOAuthLoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oauth_login, container, false);

        TextView continueTextView = view.findViewById(R.id.continue_without_account);
        continueTextView.setOnClickListener((v) -> onContinueToMain());

        FancyButton facebookLoginButton = view.findViewById(R.id.facebook_login_button);
        facebookLoginButton.setOnClickListener(v -> LoginManager.getInstance().logInWithReadPermissions(
                LandlordOAuthLoginFragment.this,
                Arrays.asList("public_profile", "email")
                // TODO request birthday
        ));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter == null)
            presenter = new LandlordOAuthLoginPresenterImpl();

        presenter.attach(this);
        presenter.onFacebookCallback(callbackManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onContinueToMain() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void onTosClick() {

    }

    @Override
    public void onContinueAccountCreation(JSONObject profile) {
        Toast.makeText(getActivity(), "onContinue", Toast.LENGTH_LONG).show();
        // CreateAccountActivity.setFragment(getFragmentManager(), UserDetailsConfirmationFragment.getInstance(profile));
    }
}
