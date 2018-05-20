package com.syzible.occupie.Landlord.AuthenticateLandlordAccount.LandlordDetailsConfirmation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LandlordDetailsConfirmationFragment extends Fragment implements LandlordDetailsConfirmationView {

    private LandlordDetailsConfirmationInteractor interactor;
    private LandlordDetailsConfirmationPresenter presenter;
    private JSONObject profile;

    private EditText forename, surname, dob, email;
    private Spinner sex;

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

        forename = view.findViewById(R.id.details_confirmation_forename);
        surname = view.findViewById(R.id.details_confirmation_surname);
        sex = view.findViewById(R.id.details_confirmation_sex);
        dob = view.findViewById(R.id.details_confirmation_dob);
        email = view.findViewById(R.id.details_confirmation_email);

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
        super.onStart();
        if (presenter == null)
            presenter = new LandlordDetailsConfirmationPresenterImpl();

        if (interactor == null)
            interactor = new LandlordDetailsConfirmationInteractorImpl(this);

        presenter.attach(this);
        presenter.parsePayload(profile);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void setForename(String forename) {
        this.forename.setText(forename);
    }

    @Override
    public void setSurname(String surname) {
        this.surname.setText(surname);
    }

    @Override
    public void setDob(String dob) {
        this.dob.setText(dob);
    }

    @Override
    public void setSex(String sex) {
        switch (sex.toLowerCase()) {
            case "male":
                this.sex.setSelection(0);
                break;
            case "female":
                this.sex.setSelection(1);
                break;
            default:
                this.sex.setSelection(2);
                break;
        }
    }

    @Override
    public void setEmail(String email) {
        this.email.setText(email);
    }

    @Override
    public String getForename() {
        return forename.getText().toString();
    }

    @Override
    public String getSurname() {
        return surname.getText().toString();
    }

    @Override
    public String getDob() {
        return dob.getText().toString();
    }

    @Override
    public String getSex() {
        return sex.getSelectedItem().toString().toLowerCase();
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public boolean isSectionCompleted() {
        return !getForename().isEmpty() && !getSurname().isEmpty() && !getEmail().isEmpty()
                && !getDob().isEmpty() && !getSex().isEmpty();
    }

    @Override
    public void createAccount(JSONObject profile) {
        try {
            interactor.createAccount(getActivity(), profile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProfileCompleted() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
