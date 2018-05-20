package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordDetailsConfirmation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.occupie.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LandlordDetailsConfirmationFragment extends Fragment implements LandlordDetailsConfirmationView {

    private LandlordDetailsConfirmationPresenter presenter;
    private JSONObject profile;

    public static LandlordDetailsConfirmationFragment getInstance(JSONObject profile) {
        LandlordDetailsConfirmationFragment fragment = new LandlordDetailsConfirmationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("oauth", profile.toString());
        fragment.setArguments(bundle);
        fragment.setProfile(profile);
        return fragment;
    }

    @Override
    public void setProfile(JSONObject profile) {
        this.profile = profile;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_confirm_oauth_details, container, false);
        FloatingActionButton next = view.findViewById(R.id.signup_next_screen);
        next.setOnClickListener(v -> {
            try {
                presenter.updateAccount();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        if (presenter == null)
            presenter = new LandlordDetailsConfirmationPresenterImpl();

        presenter.attach(this);
        presenter.parsePayload(profile);
        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();
    }

    @Override
    public void setForename(String forename) {

    }

    @Override
    public void setSurname(String surname) {

    }

    @Override
    public void setDob(String dob) {

    }

    @Override
    public void setSex(String sex) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setPhoneNumber(String phoneNumber) {

    }

    @Override
    public String getForename() {
        return null;
    }

    @Override
    public String getSurname() {
        return null;
    }

    @Override
    public String getDob() {
        return null;
    }

    @Override
    public String getSex() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public boolean isSectionCompleted() {
        return false;
    }

    @Override
    public void setNextFragment(JSONObject profile) {

    }
}
