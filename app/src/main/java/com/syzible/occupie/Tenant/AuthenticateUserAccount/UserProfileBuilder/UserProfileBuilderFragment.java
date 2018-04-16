package com.syzible.occupie.Tenant.AuthenticateUserAccount.UserProfileBuilder;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.syzible.occupie.MainActivity;
import com.syzible.occupie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class UserProfileBuilderFragment extends Fragment implements UserProfileBuilderView {
    private UserProfileBuilderPresenter presenter;
    private JSONObject profile;

    private EditText description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_details, container, false);
        description = view.findViewById(R.id.user_signup_personal_description);

        FloatingActionButton fab = view.findViewById(R.id.signup_next_screen);
        fab.setOnClickListener(v -> {
            try {
                presenter.createAccount(profile);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        return view;
    }

    public static UserProfileBuilderFragment getInstance(JSONObject profile) {
        return new UserProfileBuilderFragment().setProfile(profile);
    }

    @Override
    public void onStart() {
        if (presenter == null)
            presenter = new UserProfileBuilderPresenterImpl();

        presenter.attach(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();
    }

    public UserProfileBuilderFragment setProfile(JSONObject profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public void onProfileCompleted() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public String getDescription() {
        return "description"; // Encoding.encode(description.getText().toString());
    }

    @Override
    public JSONArray getHobbies() throws JSONException {
        return new JSONArray("[\"fighting\",\"drinking\",\"smoking\",\"partying\"]");
    }
}
