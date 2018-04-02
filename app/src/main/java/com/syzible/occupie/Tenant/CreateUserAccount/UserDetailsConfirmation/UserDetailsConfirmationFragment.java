package com.syzible.occupie.Tenant.CreateUserAccount.UserDetailsConfirmation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.syzible.occupie.R;

import org.json.JSONObject;

public class UserDetailsConfirmationFragment extends Fragment implements UserDetailsConfirmationView {

    private EditText forename, surname;
    private Spinner sex;
    private EditText dob, profession;

    private UserDetailsConfirmationPresenter presenter;
    private JSONObject oauth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_confirm_oauth_details, container, false);

        forename = view.findViewById(R.id.details_confirmation_forename);
        surname = view.findViewById(R.id.details_confirmation_surname);
        sex = view.findViewById(R.id.details_confirmation_sex);
        profession = view.findViewById(R.id.details_confirmation_profession);
        dob = view.findViewById(R.id.details_confirmation_dob);

        return view;
    }

    @Override
    public void onStart() {
        if (presenter == null)
            presenter = new UserDetailsConfirmationPresenterImpl();

        presenter.attach(this);
        presenter.parsePayload(oauth);
        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.detach();
        super.onStop();
    }

    public static UserDetailsConfirmationFragment getInstance(JSONObject oauth) {
        UserDetailsConfirmationFragment fragment = new UserDetailsConfirmationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("oauth", oauth.toString());
        fragment.setArguments(bundle);
        fragment.setOauth(oauth);
        return fragment;
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
        switch (sex) {
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
    public void setProfession(String profession) {
        this.profession.setText(profession);
    }

    public void setOauth(JSONObject oauth) {
        this.oauth = oauth;
    }
}
